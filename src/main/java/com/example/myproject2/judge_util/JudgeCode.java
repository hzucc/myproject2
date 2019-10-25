/*
 *@author ChenCheng
 *@date 2019/10/24
 */
package com.example.myproject2.judge_util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class JudgeCode {
    private final String runFilePath = "/myproject2/run";
    private final Map<String, String> runFileNameMap = new HashMap<String, String>() {{
        put("c", "Main");
        put("c/c++", "Main");
        put("java", "Main.java");
    }};
    public CompileResult compile(CompileParam compileParam) throws IOException, InterruptedException {
        List<File> files = new ArrayList<>();
        files.add(compileParam.getCompileFile());
        Docker docker = new Docker(files);
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> commands = new ArrayList<String>(){{
            add("docker");
            add("exec");
            add(docker.getDockerId());
            add("./compile");
            add(compileParam.getCodeType());
        }};
        Process process = processBuilder.command(commands).start();
        process.waitFor(4, TimeUnit.SECONDS);
        CompileResult compileResult = new CompileResult();
        if (process != null) {
            if (process.exitValue() == 0) {
                compileResult.setCompileSuccess(true);
                File workFile = new File(runFilePath + "/" + UUID.randomUUID());
                workFile.mkdirs();
                String runFileName = runFileNameMap.get(compileParam.getCodeType());
                List<String> com2 = new ArrayList<String>(){{
                    add("docker");
                    add("cp");
                    add(docker.getDockerId() + ":" + runFileName);
                    add(workFile.getPath() + "/" + runFileName);
                }};
                process = processBuilder.command(com2).start();
                process.waitFor();
                compileResult.setRunFile(new File(workFile.getPath() + "/" + runFileName));
            } else {
                compileResult.setCompileSuccess(false);
                process = Runtime.getRuntime().exec("docker exec " + docker.getDockerId() + " cat test.error");
                BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder res = new StringBuilder();
                String str = null;
                int length = 0;
                while ((str = buf.readLine()) != null && length < (1 << 10)) {
                    length += str.length();
                    res.append(str);
                    res.append('\n');
                }
                compileResult.setErrorMessage(res.toString());
                process.destroy();
            }
        } else {
            compileResult.setCompileSuccess(false);
        }
        docker.delete();
        return compileResult;
    }

    public RunResult run(RunParam runParam) throws IOException, InterruptedException {
        List<File> files = new ArrayList<>();
        files.add(runParam.getRunFile());
        files.add(runParam.getInput());
        files.add(runParam.getOutput());
        Docker docker = new Docker(files);
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> commands = new ArrayList<String>(){{
            add("docker");
            add("exec");
            add(docker.getDockerId());
            add("./runCode");
            add(String.valueOf(runParam.getTimeLimit()));
            add(String.valueOf(runParam.getMemoryLimit()));
            add(runParam.getCodeType());
        }};
        Process process = processBuilder.command(commands).start();
        RunResult runResult = new RunResult();
        String res = null;
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((res = buf.readLine()) != null) {
            if (res.contains(":")) {
                String[] split = res.split(":");
                char[] chars = split[0].toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);
                try {
                    Method method = RunResult.class.getMethod("set" + String.valueOf(chars), String.class);
                    method.invoke(runResult, split[1]);
                } catch (Exception e) {

                }
            }
        }
        process.waitFor(runParam.getTimeLimit() + 2, TimeUnit.SECONDS);
        buf.close();
        docker.delete();
        return runResult;
    }
}
