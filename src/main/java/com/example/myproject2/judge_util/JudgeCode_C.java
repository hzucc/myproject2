package com.example.myproject2.judge_util;/*
 *@author ChenCheng
 *@date 2019/10/6
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JudgeCode_C implements JudgeCode {
    private static final String runFilePath = "/myproject2/run";
    @Override
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
        CompileResult compileResult = new CompileResult();
        Process process = processBuilder.command(commands).start();
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String s = null;
        StringBuffer res = new StringBuffer();
        while ((s = buf.readLine()) != null) {
            res.append(s);
        }
        buf.close();
        compileResult.setErrorMessage(res.toString());
        process.waitFor();
        if ("running".equals(docker.getStatus()) && process.exitValue() == 0) {
            compileResult.setCompileSuccess(true);
        } else {
            compileResult.setCompileSuccess(false);
            docker.delete();
            return compileResult;
        }
        //编译阶段
        List<String> commands2 = new ArrayList<String>(){{
            add("docker");
            add("exec");
            add(docker.getDockerId());
            add("./compile2");
            add(compileParam.getCodeType());
        }};
        process = processBuilder.command(commands2).start();
        buf = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        s = null;
        res = new StringBuffer();
        while ((s = buf.readLine()) != null) {
            res.append(s);
        }
        buf.close();
        compileResult.setErrorMessage(res.toString());
        process.waitFor();
        if ("running".equals(docker.getStatus()) && process.exitValue() == 0) {
            compileResult.setCompileSuccess(true);
            File workFile = new File(runFilePath + "/" + UUID.randomUUID());
            workFile.mkdirs();
            List<String> com2 = new ArrayList<String>(){{
                add("docker");
                add("cp");
                add(docker.getDockerId() + ":/Main");
                add(workFile.getPath() + "/Main");
            }};
            process = processBuilder.command(com2).start();
            process.waitFor();
            compileResult.setRunFile(new File(workFile.getPath() + "/Main"));
        } else {
            compileResult.setCompileSuccess(false);
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
            System.out.println(res);
            if (res.contains(":")) {
                String[] split = res.split(":");
                char[] chars = split[0].toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);
                try {
                    Method method = RunResult.class.getMethod("set" + String.valueOf(chars), String.class);
                    method.invoke(runResult, split[1]);
                } catch (Exception e){

                }

            }
        }
        process.waitFor();
        buf.close();
        docker.delete();
        return runResult;
    }
}
