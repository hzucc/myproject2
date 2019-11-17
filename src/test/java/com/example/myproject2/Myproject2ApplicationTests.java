package com.example.myproject2;

import com.example.myproject2.dao.MemoryLimitDao;
import com.example.myproject2.dao.ProblemDao;
import com.example.myproject2.dao.SubmitCodeDao;
import com.example.myproject2.dao.TimeLimitDao;
import com.example.myproject2.entity.MemoryLimit;
import com.example.myproject2.entity.Problem;
import com.example.myproject2.entity.TimeLimit;
import com.example.myproject2.service.JudgeCodeService;
import com.example.myproject2.service.ProblemService;
import com.example.myproject2.service.SubmitCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Myproject2ApplicationTests {
    @Autowired
    private TimeLimitDao timeLimitDao;
    @Test
    public void contextLoads() {

    }

}
