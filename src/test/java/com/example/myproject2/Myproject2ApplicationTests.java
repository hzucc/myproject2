package com.example.myproject2;

import com.example.myproject2.dao.*;
import com.example.myproject2.entity.DockerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Myproject2ApplicationTests {
    @Autowired
    private DockerFactory dockerFactory;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SubmitCodeDao submitCodeDao;
    @Test
    public void contextLoads() {
    }

}
