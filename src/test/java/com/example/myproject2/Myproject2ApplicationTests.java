package com.example.myproject2;

import com.example.myproject2.dao.TimeLimitDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Myproject2ApplicationTests {
    @Autowired
    private TimeLimitDao timeLimitDao;
    @Test
    public void contextLoads() {

    }

}
