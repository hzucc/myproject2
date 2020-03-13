/*
 *@author ChenCheng
 *@date 2019/10/14
 */
package com.example.myproject2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class AfterStrartingSpringboot implements CommandLineRunner {
    @Value("${myproject2_data}")
    private String myproject2_data;

    @Override
    public void run(String... args) throws IOException, InterruptedException {
        Logger logger = Logger.getLogger("AfterClosingSpringboot");
        logger.info("执行springboot启动就绪时任务...");
        ProcessBuilder processBuilder = new ProcessBuilder();

        //软连接到资源目录下的/myproject2_data，或初始化
        String path = getClass().getClassLoader().getResource("/").getPath();
        List<String> commands;
        if (myproject2_data != null && !myproject2_data.equals("")) {
            String fromFilePath = myproject2_data;
            String toFilePath = path + "/myproject2_data";
            commands = new ArrayList<String>() {{
                add("ln");
                add("-s");
                add(fromFilePath);
                add(toFilePath);
            }};
        } else {
            String fromFilePath = path + "/data/myproject2_data";
            String toFilePath = path + "/myproject2_data";
            commands = new ArrayList<String>() {{
                add("cp");
                add("-r");
                add(fromFilePath);
                add(toFilePath);
            }};
        }
        processBuilder.command(commands).start().waitFor();
        //检查是否配置docker
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("docker images");
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String s;
        StringBuilder str = new StringBuilder();
        while ((s = buf.readLine()) != null) {
            str.append(s);
        }
        process.waitFor();
        boolean hashDocker = str.indexOf("hzucc/alpine-oj") != -1;
        if (!hashDocker) {
            logger.info("未安装docker或还没有拉取hzucc/alpine-oj镜像");
        } else {
            logger.info("检测到docker和hzucc/alpine-oj镜像");
        }
        buf.close();
        process.waitFor();

        //查询是否安装mysql
        processBuilder = new ProcessBuilder();
        commands = new ArrayList<String>() {{
            add("mysql");
            add("-V");
        }};
        process = processBuilder.command(commands).start();
        buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        s = buf.readLine();
        boolean hasMysql = s != null && s.contains("Ver");
        buf.close();
        process.waitFor();
        if (!hasMysql) {
            logger.info("还没有安装mysql");
        } else {
            //检查数据库
            processBuilder = new ProcessBuilder();
            commands = new ArrayList<String>() {{
                add("mysql");
                add("-uroot");
                add("-p123456");
                add("-e");
                add("show databases");
            }};
            process = processBuilder.command(commands).start();
            buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            boolean hashDatabase = false;
            while ((s = buf.readLine()) != null) {
                if ("myproject2".equals(s)) {
                    hashDatabase = true;
                    break;
                }
            }
            buf.close();
            process.waitFor();
            if (!hashDatabase) {
                logger.info("还没有数据库,正在导入...");
                //创建数据库
                commands = new ArrayList<String>() {{
                    add("mysql");
                    add("-uroot");
                    add("-p123456");
                    add("-e");
                    add("create database myproject2");
                }};
                processBuilder.command(commands).start().waitFor();
                //导入数据
                commands = new ArrayList<String>() {{
                    add("mysql");
                    add("-uroot");
                    add("-p123456");
                    add("myproject2");
                }};
                File sqlFile = new File(getClass().getClassLoader().getResource("/data/myproject2.sql").getFile());
                processBuilder.redirectInput(sqlFile);
                processBuilder.command(commands).start().waitFor();
                logger.info("导入数据库完毕");
            }
        }
        logger.info("执行springboot启动就绪时任务--完毕！");
    }
}
