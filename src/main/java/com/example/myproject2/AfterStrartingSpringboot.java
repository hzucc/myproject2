/*
 *@author ChenCheng
 *@date 2019/10/14
 */
package com.example.myproject2;

import com.example.myproject2.service.UserService;
import com.example.myproject2.util.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class AfterStrartingSpringboot implements CommandLineRunner {
    @Value("${myproject2.testDataPath}")
    private String testDataPath;

    @Value("${myproject2.compile}")
    private String compilePath;

    @Value("${myproject2.run}")
    private String runPath;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws IOException, InterruptedException {
        Logger logger = Logger.getLogger("AfterClosingSpringboot");
        logger.info("执行springboot启动就绪时任务...");
        ProcessBuilder processBuilder = new ProcessBuilder();

        //检测目录是否存在
        File testDataFile = new File(testDataPath);
        if (!testDataFile.exists()) {
            testDataFile.mkdirs();
        }
        File compileFile = new File(compilePath);
        if (!compileFile.exists()) {
            compileFile.mkdirs();
        }
        File runFile = new File(runPath);
        if (!runFile.exists()) {
            runFile.mkdirs();
        }
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
        List<String> commands = new ArrayList<String>() {{
            add("mysql");
            add("-V");
        }};
        process = processBuilder.command(commands).start();
        buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        boolean hasMysql = buf.readLine() != null;
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
                File sqlFile = new File(getClass().getClassLoader().getResource("/myproject2.sql").getFile());
                processBuilder.redirectInput(sqlFile);
                processBuilder.command(commands).start().waitFor();
                logger.info("导入数据库完毕");
            }
        }
        logger.info("执行springboot启动就绪时任务--完毕！");

    }
}
