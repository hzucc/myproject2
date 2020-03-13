package com.example.myproject2.judge_util;

import com.example.myproject2.entity.DockerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JudgeCode {
    @Autowired
    private DockerFactory dockerFactory;
    private String runFilePath;
    private boolean testEnv;
    private final Map<String, String> runFileNameMap;

    public JudgeCode() {
        runFileNameMap = new HashMap<String, String>() {{
            put("c", "Main");
            put("c_cpp", "Main");
            put("java", "Main.class");
            put("go", "Main");
            put("python3", "Main.pyc");
        }};
        testEnv = false;
        runFilePath = getClass().getClassLoader().getResource("/").getPath() + "/myproject2_data/run";
    }

    public CompileResult compile(CompileParam compileParam) throws IOException, InterruptedException {
        Docker docker = this.dockerFactory.dockerPull();
        List<File> files = new ArrayList();
        files.add(compileParam.getCompileFile());
        Runtime runtime = Runtime.getRuntime();

        String s = compileParam.getCompileFile().getPath();
        String compileFilePath = s.substring(s.indexOf("/myproject2_data"));

        Process process = runtime.exec("docker exec " + docker.getDockerId() + " ./compile " + compileParam.getCodeType() + " " + compileFilePath);
        process.waitFor(3L, TimeUnit.SECONDS);
        CompileResult compileResult = new CompileResult();
        if (process != null) {
            switch (process.exitValue()) {
                case -1:
                    compileResult.setCompileSuccess(false);
                    compileResult.setErrorMessage("未知错误");
                    break;
                case 32:
                    compileResult.setCompileSuccess(true);
                    File workFile = new File(this.runFilePath, UUID.randomUUID().toString());
                    workFile.mkdir();
                    String runFileName = (String) this.runFileNameMap.get(compileParam.getCodeType());
                    String runFilePath = workFile.getPath() + "/" + runFileName;
                    runtime.exec("docker cp " + docker.getDockerId() + ":" + runFileName + " " + runFilePath).waitFor();
                    compileResult.setRunFile(new File(runFilePath));
                    break;
                case 33:
                    compileResult.setCompileSuccess(false);
                    compileResult.setErrorMessage("源文件长度超过64KB");
                case 34:
                    break;
                case 35:
                    compileResult.setCompileSuccess(false);
                    process = Runtime.getRuntime().exec("docker exec " + docker.getDockerId() + " cat test.error");
                    BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    StringBuilder res = new StringBuilder();
                    String str = null;
                    int length = 0;

                    while ((str = buf.readLine()) != null && length < 1024) {
                        length += str.length();
                        res.append(str);
                        res.append('\n');
                    }

                    compileResult.setErrorMessage(res.toString());
                    process.destroy();
                    break;
                default:
                    compileResult.setCompileSuccess(false);
            }
        } else {
            compileResult.setCompileSuccess(false);
        }

        this.dockerFactory.dockerPush(docker);

        return compileResult;
    }

    public RunResult run(RunParam runParam) throws IOException, InterruptedException {
        Docker docker = this.dockerFactory.dockerPull();
        Runtime runtime = Runtime.getRuntime();
        String s;
        s = runParam.getRunFile().getPath();
        String runFilePath = s.substring(s.indexOf("/myproject2_data"));
        s = runParam.getInput().getPath();
        String inputFilePath = s.substring(s.indexOf("/myproject2_data"));
        s = runParam.getOutput().getPath();
        String outputFilePath = s.substring(s.indexOf("/myproject2_data"));

        Process process = runtime.exec("docker exec " + docker.getDockerId() + " ./runCode " + runParam.getTimeLimit() + " " + runParam.getMemoryLimit() + " " + runParam.getCodeType() + " " + runFilePath + " " + inputFilePath + " " + outputFilePath);
        RunResult runResult = new RunResult();
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String res;
        while ((res = buf.readLine()) != null) {
            if (res.contains(":")) {
                String[] split = res.split(":");
                char[] chars = split[0].toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);

                try {
                    Method method = RunResult.class.getMethod("set" + String.valueOf(chars), String.class);
                    method.invoke(runResult, split[1]);
                } catch (Exception var11) {
                }
            }
        }

        process.waitFor((long) (runParam.getTimeLimit() + 1), TimeUnit.SECONDS);
        if (process != null) {
            switch (process.exitValue()) {
                case -1:
                    runResult.setResult("未知错误");
                    break;
                case 32:
                    runResult.setResult("AC");
                    break;
                case 33:
                    runResult.setResult("WA");
                    break;
                case 34:
                    runResult.setResult("TL");
                    break;
                case 35:
                    runResult.setResult("ML");
                    break;
                case 36:
                    runResult.setResult("RE");
                    break;
                case 99:
                    runResult.setResult("OE");
                    break;
                default:
                    runResult.setResult("WA");
            }
        } else {
            runResult.setResult("WA");
        }

        buf.close();

        this.dockerFactory.dockerPush(docker);


        return runResult;
    }
}
