package com.example.myproject2.judge_util;/*
 *@author ChenCheng
 *@date 2019/9/27
 */

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Docker {
    private final List<String> commands = new ArrayList<String>(){{
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
        //设置OI,按实际设置/dev/sda  ，  还是 /dev/vda
        add("--device-read-bps");
        add("/dev/sda:50mb");
        add("--device-write-bps");
        add("/dev/sda:50mb");

    }};
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