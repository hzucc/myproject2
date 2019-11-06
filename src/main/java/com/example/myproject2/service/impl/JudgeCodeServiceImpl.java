/*
 *@author ChenCheng
 *@date 2019/10/9
 */
package com.example.myproject2.service.impl;

import com.example.myproject2.dao.ProblemDao;
import com.example.myproject2.dao.RunCodeDao;
import com.example.myproject2.dao.SubmitCodeDao;
import com.example.myproject2.dao.TestDataDao;
import com.example.myproject2.entity.RunCode;
import com.example.myproject2.entity.SubmitCode;
import com.example.myproject2.judge_util.*;
import com.example.myproject2.service.JudgeCodeService;
import com.example.myproject2.service.ProblemService;
import com.example.myproject2.service.RunCodeService;
import com.example.myproject2.service.SubmitCodeService;
import com.example.myproject2.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class JudgeCodeServiceImpl implements JudgeCodeService{
    @Value("${myproject2.compile}")
    private String compileWorkPath;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SubmitCodeDao submitCodeDao;
    @Autowired
    private TestDataDao testDataDao;
    @Autowired
    private RunCodeDao runCodeDao;
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private JudgeCode judgeCode;
    @Autowired
    private SubmitCodeService submitCodeService;
    @Autowired
    private RunCodeService runCodeService;

    @Async
    @Scheduled(fixedRate = 100)
    @Transactional
    @Override
    public void compilePL() throws IOException, InterruptedException {
        SubmitCode submitCode = submitCodeService.submitCodePull();
        if (submitCode != null) {
            int submitCodeId = submitCode.getSubmitCodeId();
            int problemId = submitCode.getProblemId();
            String codeType = submitCode.getCodeType();
            String codeValue = submitCode.getCodeValue();
            CompileParam compileParam = new CompileParam(codeType, new File(codeValue));
            CompileResult compileResult = judgeCode.compile(compileParam);
            if (compileResult.isCompileSuccess()) {
                List<String> testDataPaths = testDataDao.selectTestDataPath(problemId);
                if (testDataPaths != null && !testDataPaths.isEmpty()) {
                    submitCodeDao.updateStatusAndTestNum(submitCodeId, "running", (short) testDataPaths.size());
                    List<RunCode> runCodes = new ArrayList<>();
                    for (int j = 0; j < testDataPaths.size(); j++) {
                        RunCode runCode = applicationContext.getBean(RunCode.class, submitCodeId, compileResult.getRunFile().getPath(), codeType, testDataPaths.get(j), (short)(j + 1));
                        runCodes.add(runCode);
                    }
                    runCodeDao.insertRunCodes(runCodes);
                }
            } else {
                submitCodeDao.updateStatusAndCompileMessage(submitCodeId, "compile fail", compileResult.getErrorMessage());
            }
        }
    }

    @Async
    @Scheduled(fixedRate = 100)
    @Transactional
    @Override
    public void runPL() throws InterruptedException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        RunCode runCode = runCodeService.runCodePull();
        if (runCode != null) {
            int runCodeId = runCode.getRunCodeId();
            String codeType = runCode.getCodeType();
            int submitCodeId = runCode.getSubmitCodeId();
            String runCodeFile = runCode.getRunCodeFile();
            String testDataPath = runCode.getTestDataPath();
            int problemId = submitCodeDao.selectProblemId(submitCodeId);
            List<Short> problemLimit = problemService.getProblemLimit(problemId, codeType);
            int timeLimit = problemLimit.get(0);
            int memoryLimit = problemLimit.get(1);
            RunParam runParam = new RunParam(codeType, new File(runCodeFile), new File(testDataPath, "test.in"),
                    new File(testDataPath, "test.out"), timeLimit, memoryLimit);
            RunResult runResult = judgeCode.run(runParam);
            short runTime = atos(runResult.getRunTime());
            short runMemory = atos(runResult.getRunMemory());
            String result = submitCodeService.setJudgeTestNum(submitCodeId, runCodeId, runResult.getResult(), runTime, runMemory);
            if (!result.equals("running")) {
                if (result.equals("AC")) {
                    problemDao.updateAcceptNum(problemId);
                }
                submitCodeDao.updateStatus(submitCodeId, result);
                FileUtil.delete(runParam.getRunFile().getParentFile());
            }
        }
    }
    private short atos(String s) {
        short x = 0;
        try {
            x = Short.valueOf(String.valueOf(s));
        } catch (Exception e) {

        }
        return x;
    }
}
