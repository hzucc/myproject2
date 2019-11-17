/*
 *@author ChenCheng
 *@date 2019/11/7
 */
package com.example.myproject2;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class AfterClosingSpringboot implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        Logger logger = Logger.getLogger("AfterClosingSpringboot");
        logger.info("执行springboot结束时任务...");
        deleteDocker();
        logger.info("执行springboot结束时任务--完毕！");
    }

    private void deleteDocker() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        //获取所有dockerId
        List<String> commands = new ArrayList<>();
        commands.add("docker");
        commands.add("ps");
        commands.add("-aq");
        Process process = processBuilder.command(commands).start();
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<String> dockerIds = new ArrayList<>();
        String s = null;
        while ((s = buf.readLine()) != null) {
            dockerIds.add(s);
        }
        buf.close();
        process.waitFor();
        //kill所有docker
        commands.clear();
        commands.add("docker");
        commands.add("kill");
        commands.addAll(dockerIds);
        processBuilder.command(commands).start().waitFor();
        //删除所有docker
        commands.clear();
        commands.add("docker");
        commands.add("rm");
        commands.addAll(dockerIds);
        processBuilder.command(commands).start().waitFor();
    }
}
