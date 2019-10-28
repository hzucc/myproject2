/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> commands = new ArrayList<>();
        commands.add("docker");
        commands.add("run");
        commands.add("-itd");
        commands.add("hzucc/alpine-oj");
        Process start = processBuilder.command(commands).start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));
        String dockerId = bufferedReader.readLine();
        bufferedReader.close();
    }
}
