/*
 *@author ChenCheng
 *@date 2019/10/14
 */
package com.example.myproject2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

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
        System.out.println("Init类的初始化工作完毕");
    }
}
