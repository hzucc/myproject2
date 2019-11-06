/*
 *@author ChenCheng
 *@date 2019/10/25
 */
package com.example.myproject2.entity;

import com.example.myproject2.judge_util.Docker;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
public class DockerFactory {

    private final int maxActiveDocker = 100;

    private final int destroyQueueCapacity = 1000;

    private BlockingQueue<Docker> activeDockers = new ArrayBlockingQueue<>(maxActiveDocker);

    private BlockingQueue<Docker> dockerDestroys = new ArrayBlockingQueue<>(destroyQueueCapacity);

    private final TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>() {{
        put(0, 5);
        put(20, 4);
        put(40, 3);
        put(60, 2);
        put(80, 1);
        put(100, 0);
    }};
    /*
    * docker生产
    *  剩余容量     每5秒生产数量
    * [0%,20%) :      5个
    * [20%,40%) :     4个
    * [40%,60%) :     3个
    * [60%,80%) :     2个
    * [80%,100%) :    1个
    * [100%,100%] :   0个
    *
    * */
    @Scheduled(fixedRate = 5000)
    @Async
    public void createDocker() throws IOException, InterruptedException {
        int currentNum = activeDockers.size();
        int f = (int) ((double)currentNum / maxActiveDocker * 100);
        int createNum = treeMap.floorEntry(f).getValue();
        while(createNum-- > 0) {
            Docker docker = new Docker();
            boolean isOffer = false;
            try {
                isOffer = activeDockers.offer(docker);
            } catch (Exception e) {

            } finally {
                if (!isOffer) {
                    docker.delete();
                }
            }
        }
    }

    /*
    * docker删除
    * 每10秒最多删除50个
    * */
    @Scheduled(fixedRate = 10000)
    @Async
    public void destroyDocker() throws IOException, InterruptedException {
        int maxDestroyNum = 50;
        List<Docker> dockers = new ArrayList();
        try {
            dockerDestroys.drainTo(dockers, maxDestroyNum);
        } catch (Exception e) {

        }
        if (!dockers.isEmpty()) {
            ProcessBuilder processBuilder = new ProcessBuilder();
            List<String> commands = new ArrayList<>();
            commands.add("docker");
            commands.add("kill");
            for (Docker docker: dockers) {
                commands.add(docker.getDockerId());
            }
            processBuilder.command(commands).start().waitFor();
            commands.clear();
            commands.add("docker");
            commands.add("rm");
            for (Docker docker: dockers) {
                commands.add(docker.getDockerId());
            }
            processBuilder.command(commands).start();
        }
    }

    public void dockerPush(Docker docker) throws InterruptedException {
        dockerDestroys.put(docker);
    }

    public Docker dockerPull() throws InterruptedException {
        return activeDockers.take();
    }
}
