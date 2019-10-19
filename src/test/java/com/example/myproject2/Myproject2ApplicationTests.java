package com.example.myproject2;

import com.example.myproject2.dao.*;
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
    private RunCodeDao runCodeDao;
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SubmitCodeDao submitCodeDao;
    @Autowired
    private User_RoleDao user_roleDao;

    @Test
    public void contextLoads() {
        user_roleDao.insertUser_Role("123@qq.com", "ROLE_USER");
    }

}
