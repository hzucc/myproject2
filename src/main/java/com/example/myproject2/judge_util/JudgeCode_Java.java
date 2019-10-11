/*
 *@author ChenCheng
 *@date 2019/10/9
 */
package com.example.myproject2.judge_util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class JudgeCode_Java implements JudgeCode {
    private String runFilePath = "myproject2.run";
    @Override
    public CompileResult compile(CompileParam compileParam) throws IOException, InterruptedException {
        List<File> files = new ArrayList<>();
        files.add(compileParam.getCompileFile());
        Docker docker = new Docker(files);
        //编译阶段
        List<String> commands2 = new ArrayList<String>(){{
            add("docker");
            add("exec");
            add(docker.getDockerId());
            add("./compile2");
            add(compileParam.getCodeType());
        }};
        ProcessBuilder processBuilder = new ProcessBuilder();
        Process process = processBuilder.command(commands2).start();
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String res = buf.readLine();
        process.waitFor();
        buf.close();
        CompileResult compileResult = new CompileResult();
        if ("running".equals(docker.getStatus()) && "success:compile success".equals(res)) {
            compileResult.setCompileSuccess(true);
            File workFile = new File(runFilePath + "/" + UUID.randomUUID());
            workFile.mkdirs();
            List<String> com2 = new ArrayList<String>(){{
                add("docker");
                add("cp");
                add(docker.getDockerId() + ":/Main.class");
                add(workFile.getPath() + "/Main.class");
            }};
            process = processBuilder.command(com2).start();
            process.waitFor();
            compileResult.setRunFile(new File(workFile.getPath() + "/Main.class"));
        } else {
            compileResult.setCompileSuccess(false);
            List<String> com2 = new ArrayList<String>() {{
                add("docker");
                add("exec");
                add(docker.getDockerId());
                add("cat");
                add("test.error");
            }};
            process = processBuilder.command(com2).start();
            buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuffer error = new StringBuffer();
            String s = null;
            while ((s = buf.readLine()) != null) {
                error.append(s);
            }
            compileResult.setErrorMessage(error.toString());
            buf.close();
            process.waitFor();
        }
        docker.delete();
        return compileResult;
    }

    @Override
    public RunResult run(RunParam runParam) throws IOException, InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
                Method method = RunResult.class.getMethod("set" + String.valueOf(chars), String.class);
                method.invoke(runResult, split[1]);
            }
        }
        process.waitFor();
        buf.close();
        docker.delete();
        return runResult;
    }
}
