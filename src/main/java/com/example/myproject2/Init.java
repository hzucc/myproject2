/*
 *@author ChenCheng
 *@date 2019/10/14
 */
package com.example.myproject2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.logging.Logger;

@Component
public class Init implements CommandLineRunner {
    @Value("${myproject2.testDataPath}")
    private String testDataPath;

    @Value("${myproject2.compile}")
    private String compilePath;

    @Value("${myproject2.run}")
    private String runPath;

    @Override
    public void run(String... args) throws Exception {
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
        Runtime runtime = Runtime.getRuntime();
  /*      System.out.println("开始清理docker");
        runtime.exec("docker kill  $(docker ps -aq)").waitFor();
        runtime.exec("docker rm  $(docker ps -aq)").waitFor();
        System.out.println("清理完毕");*/
        Logger.getLogger("Init-log").info("Init类初始化完毕");

    }
}
