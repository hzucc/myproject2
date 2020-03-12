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
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private com.example.myproject2.util.Test test;

    @Override
    public void run(String... args) throws IOException, InterruptedException {
        Logger logger = Logger.getLogger("AfterClosingSpringboot");
        logger.info("执行springboot启动就绪时任务...");
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
        logger.info("执行springboot启动就绪时任务--完毕！");

    }
}
