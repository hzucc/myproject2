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
import com.example.myproject2.judge_util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class JudgeCodeImpl implements com.example.myproject2.service.JudgeCode {
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

    @Transactional
    @Override
    @Async
    @Scheduled(fixedRate=1000)
    public void getCompilePL() throws IOException, ClassNotFoundException, InterruptedException, InstantiationException, IllegalAccessException {
        List<Map<String ,String>> codes = submitCodeDao.selectCodeInWaiting(3);
        if (codes != null && !codes.isEmpty()) {
            List<Integer> submitCodeIds = new ArrayList<>();
            for (Map<String, String> map: codes) {
                submitCodeIds.add(Integer.valueOf(String.valueOf(map.get("submitCodeId"))));
            }
            submitCodeDao.updateStatusList(submitCodeIds, "compiling");
            for (Map<String, String> map: codes) {
                compilePL(map);
            }
        }
    }
    @Override
    @Async
    public void compilePL(Map<String ,String> code) throws IOException, InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        int submitCodeId = Integer.valueOf(String.valueOf(code.get("submitCodeId")));
        int problemId = Integer.valueOf(String.valueOf(code.get("problemId")));
        String codeType = code.get("codeType");
        String codeValue = code.get("codeValue");
        CompileParam compileParam = new CompileParam();
        compileParam.setCodeType(codeType);
        compileParam.setCompileFile(new File(codeValue));
        JudgeCode judgeCode = (JudgeCode) Class.forName("com.example.myproject2.judge_util.JudgeCode_" + HandleType.typeHandle(codeType)).newInstance();
        CompileResult compileResult = judgeCode.compile(compileParam);
        if (compileResult.isCompileSuccess()) {
            submitCodeDao.updateStatus(submitCodeId, "compile success");
            List<String> testDataPaths = testDataDao.selectTestDataPath(problemId);
            if (testDataPaths != null && !testDataPaths.isEmpty()) {
                submitCodeDao.updateStatus(submitCodeId, "running");
                submitCodeDao.updateTestNumber(testDataPaths.size(), submitCodeId);
                List<RunCode> runCodes = new ArrayList<>();
                for (int i = 0; i < testDataPaths.size(); i++) {
                    RunCode runCode = applicationContext.getBean(RunCode.class);
                    runCode.setCodeType(codeType);
                    runCode.setRunCodeFile(compileResult.getRunFile().getPath());
                    runCode.setSubmitCodeId(submitCodeId);
                    runCode.setTestDataSerial(i + 1);
                    runCode.setTestDataPath(testDataPaths.get(i));
                    runCodes.add(runCode);
                }
                runCodeDao.insertRunCodes(runCodes);
            }
        } else {
            submitCodeDao.updateStatus(submitCodeId, "compile fail");
        }
    }

    @Transactional
    @Override
    @Async
    @Scheduled(fixedRate=1000)
    public void getRunPL() throws IllegalAccessException, InstantiationException, ClassNotFoundException, InterruptedException, NoSuchMethodException, IOException, InvocationTargetException {
        List<Map<String, String>> list = runCodeDao.selectRunCodes(10);
        if (list != null && !list.isEmpty()) {
            List<Integer> runCodeIds = new ArrayList<>();
            for (Map<String, String> map: list) {
                runCodeIds.add(Integer.valueOf(String.valueOf(map.get("runCodeId"))));
            }
            runCodeDao.updateStatus(runCodeIds, "running");
            for (Map<String, String> map: list) {
                runPL(map);
            }
        }
    }

    @Async
    @Override
    public void runPL(Map<String, String> map) throws ClassNotFoundException, InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, InstantiationException {
        String codeType = map.get("codeType");
        int submitCodeId = Integer.valueOf(String.valueOf(map.get("submitCodeId")));
        int runCodeId = Integer.valueOf(String.valueOf(map.get("runCodeId")));
        String runCodeFile = map.get("runCodeFile");
        String testDataPath = map.get("testDataPath");
        int problemId = submitCodeDao.selectProblemId(submitCodeId);
        Map<String, String> limit = problemDao.selectLimit(problemId, codeType);
        int timeLimit = Integer.valueOf(String.valueOf(limit.get("timeLimit")));
        int memoryLimit = Integer.valueOf(String.valueOf(limit.get("memoryLimit")));
        RunParam runParam = new RunParam();
        runParam.setCodeType(codeType);
        runParam.setInput(new File(testDataPath + "/test.in"));
        runParam.setOutput(new File(testDataPath + "/test.out"));
        runParam.setTimeLimit(timeLimit);
        runParam.setMemoryLimit(memoryLimit);
        runParam.setRunFile(new File(runCodeFile));
        JudgeCode judgeCode = (JudgeCode) Class.forName("com.example.myproject2.judge_util.JudgeCode_" + HandleType.typeHandle(codeType)).newInstance();
        RunResult runResult = judgeCode.run(runParam);
        int runTime = runResult.getRunTime();
        int runMemory = runResult.getRunMemory();
        runCodeDao.updateJudgeStatus(runCodeId, runResult.getResult(), runTime, runMemory);
        submitCodeDao.updateJudgeTestNumber(submitCodeId);

        int testNumber = submitCodeDao.selectTestNumber(submitCodeId);
        int judgeTestNumber = submitCodeDao.selectJudgeTestNumber(submitCodeId);
        boolean isJudgeCompletion = false;
        if (testNumber == judgeTestNumber) {
            isJudgeCompletion = true;
        }
        if (isJudgeCompletion) {
            List<String> status = submitCodeDao.selectJudgeStatus(submitCodeId);
            String result = "AC";
            for (String s: status) {
                if (!"AC".equals(s)) {
                    result = s;
                    break;
                }
            }
            submitCodeDao.updateJudgeStatus(submitCodeId, result);
        }

    }
}
