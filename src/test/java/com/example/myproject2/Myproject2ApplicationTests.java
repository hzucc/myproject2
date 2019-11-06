package com.example.myproject2;

import com.example.myproject2.dao.ProblemDao;
import com.example.myproject2.dao.SubmitCodeDao;
import com.example.myproject2.dao.SubmitCodeDaoResult.SubmitCode1;
import com.example.myproject2.entity.Problem;
import com.example.myproject2.service.JudgeCodeService;
import com.example.myproject2.service.ProblemService;
import com.example.myproject2.service.SubmitCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class Myproject2ApplicationTests {
    @Autowired
    private JudgeCodeService judgeCodeService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SubmitCodeService submitCodeService;
    @Autowired
    private SubmitCodeDao submitCodeDao;
    @Autowired
    private ProblemService problemService;
    @Test
    public void contextLoads() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Short> problemLimit = problemService.getProblemLimit(1041, "c/c++");
        System.out.println(problemLimit);
    }

}
