package com.example.myproject2;

import com.example.myproject2.dao.TimeLimitDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import sun.awt.AppContext;

import java.io.File;
import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Myproject2ApplicationTests {
    @Autowired
    private TimeLimitDao timeLimitDao;
    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void contextLoads() throws IOException {
        File[] files = applicationContext.getResource("/").getFile().listFiles();
        for (File file: files) {
            System.out.println(file.getName());
        }
    }

}
