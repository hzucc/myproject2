/*
 *@author ChenCheng
 *@date 2019/10/25
 */
package com.example.myproject2.entity;

import com.example.myproject2.judge_util.Docker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    private BlockingQueue<Docker> dockerDestroys = new ArrayBlockingQueue<>(1000);

    @Scheduled(fixedRate = 1000)
    public void createDocker() throws IOException, InterruptedException {
        if (dockers.size() < 100) {
            Docker docker = new Docker();
            dockers.add(docker);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void destroyDocker() throws IOException, InterruptedException {
        List<Docker> dockers = new ArrayList<>();
        dockerDestroys.drainTo(dockers, 10);
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
