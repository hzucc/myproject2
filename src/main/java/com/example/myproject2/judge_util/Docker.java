package com.example.myproject2.judge_util;/*
 *@author ChenCheng
 *@date 2019/9/27
 */


import org.springframework.scheduling.annotation.Async;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Docker {
    private static final List<String> commands = new ArrayList<String>(){{
        add("docker");
        add("run");
        add("-itd");
        //设置容器网络
        add("--network");
        add("none");
        //设置容器内存
        add("-m");
        add("1024m");
        add("--memory-swap");
        add("1152m");
        /*设置OI,
        值得注意的是，本机是挂载路径是/dev/sda, 阿里云服务器是/dev/vda...
        */
        add("--device-read-bps");
        add("/dev/sda:50mb");
        add("--device-write-bps");
        add("/dev/sda:50mb");
    }};
    private String dockerId;

    public String getDockerId() {
        return dockerId;
    }

    public Docker(List<File> files) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> com = new ArrayList<>(commands);
        for (File file: files) {
            String name = file.getName();
            if (name.startsWith("Main") && name.contains(".")) {
                name = "Main" + name.substring(name.lastIndexOf("."));
            }
            com.add("-v");
            com.add(file.getPath() + ":/" + name + ":ro");
        }
        com.add("hzucc/alpine-oj");
        Process process = processBuilder.command(com).start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        dockerId = bufferedReader.readLine();
        bufferedReader.close();
        process.waitFor();
    }


    public String getStatus() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("docker inspect -f {{.State.Status}} " + dockerId);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String status = bufferedReader.readLine();
        process.waitFor();
        return status;
    }

    @Async
    public void delete() throws IOException, InterruptedException {
        String dockerStatus = getStatus();
        ProcessBuilder processBuilder = new ProcessBuilder();
        if ("running".equals(dockerStatus)) {
            List<String> commands = new ArrayList<String>() {{
                add("docker");
                add("kill");
                add(dockerId);
            }};
            Process process = processBuilder.command(commands).start();
            process.waitFor();
        }
        List<String> commands = new ArrayList<String>() {{
            add("docker");
            add("rm");
            add(dockerId);
        }};
        processBuilder.command(commands).start();
    }
}