package com.example.myproject2;

import com.example.myproject2.dao.ProblemDao;
import com.example.myproject2.dao.UserDao;
import com.example.myproject2.entity.MemoryLimit;
import com.example.myproject2.entity.Problem;
import com.example.myproject2.entity.UpdateTestDataMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Myproject2ApplicationTests {
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UpdateTestDataMap updateTestDataMap;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads() {

    }

}
