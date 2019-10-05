package com.example.myproject2;

import com.example.myproject2.dao.*;
import com.example.myproject2.entity.*;
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
    @Autowired
    private TimeLimitDao timeLimitDao;
    @Autowired
    private MemoryLimitDao memoryLimitDao;
    @Autowired
    private SubmitCodeDao submitCodeDao;
    @Autowired
    private TableCountDao tableCountDao;

    @Test
    public void contextLoads() {
        tableCountDao.selectTableCount("submit_code");
    }

}
