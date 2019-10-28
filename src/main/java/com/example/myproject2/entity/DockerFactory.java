/*
 *@author ChenCheng
 *@date 2019/10/25
 */
package com.example.myproject2.entity;

import com.example.myproject2.judge_util.Docker;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
public class DockerFactory {
    private BlockingQueue<Docker> dockers = new ArrayBlockingQueue<>(100);
    private BlockingQueue<Docker> dockerDestroys = new ArrayBlockingQueue<>(200);

    @Scheduled(fixedRate = 5000)
    public void createDocker() throws IOException, InterruptedException {
        int createNum = 5;
        while (dockers.size() < 100 && createNum-- > 0) {
            Docker docker = new Docker();
            dockers.add(docker);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void destroyDocker() throws IOException, InterruptedException {
        List<Docker> dockers = new ArrayList<>();
        int destroyNum = 30;
        dockerDestroys.drainTo(dockers, destroyNum);
        if (!dockers.isEmpty()) {
            for (Docker docker: dockers) {
                docker.delete();
            }
        }
    }

    public Docker getDocker() throws InterruptedException {
        return dockers.take();
    }

    public void deleteDocker(Docker docker) throws InterruptedException {
        dockerDestroys.put(docker);
    }
}
