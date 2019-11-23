package com.example.myproject2.judge_util;/*
 *@author ChenCheng
 *@date 2019/9/27
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Docker {
    private static List<String> commands = new ArrayList<String>(){{
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
        //设置cpu核心数为1
        add("--cpus");
        add("1");
    }};
    static {
        if (new File("/dev/sda").exists()) {
            commands.add("--device-read-bps");
            commands.add("/dev/sda:50mb");
            commands.add("--device-write-bps");
            commands.add("/dev/sda:50mb");
        } else {
            commands.add("--device-read-bps");
            commands.add("/dev/vda:50mb");
            commands.add("--device-write-bps");
            commands.add("/dev/vda:50mb");
        }
    }
    private String dockerId;

    public String getDockerId() {
        return dockerId;
    }

    public Docker() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> com = new ArrayList<>(commands);
        com.add("-v");
        com.add("/myproject2:/myproject2:ro");
        com.add("hzucc/alpine-oj");
        Process process = processBuilder.command(com).start();
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        dockerId = buf.readLine();
        buf.close();
        process.waitFor();
    }

    public void delete() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("docker kill " + dockerId).waitFor();
        runtime.exec("docker rm " + dockerId);
    }
}