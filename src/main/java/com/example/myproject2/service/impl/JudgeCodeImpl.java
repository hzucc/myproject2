/*
 *@author ChenCheng
 *@date 2019/10/9
 */
package com.example.myproject2.service.impl;

import com.example.myproject2.dao.ProblemDao;
import com.example.myproject2.dao.RunCodeDao;
import com.example.myproject2.dao.SubmitCodeDao;
import com.example.myproject2.dao.TestDataDao;
import com.example.myproject2.entity.CompilePLQueue;
import com.example.myproject2.entity.RunCode;
import com.example.myproject2.entity.RunPLQueue;
import com.example.myproject2.judge_util.*;
import com.example.myproject2.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

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
    @Autowired
    private CompilePLQueue compilePLQueue;
    @Autowired
    private RunPLQueue runPLQueue;

    @Scheduled(fixedRate = 100)
    @Async
    @Override
    public void compilePL() throws IOException, InterruptedException {
        Map<String, String> map = compilePLQueue.pull();
        if (map != null && !map.isEmpty()) {
            int submitCodeId = Integer.valueOf(String.valueOf(map.get("submitCodeId")));
            int problemId = Integer.valueOf(String.valueOf(map.get("problemId")));
            String codeType = map.get("codeType");
            String codeValue = map.get("codeValue");
            CompileParam compileParam = new CompileParam(codeType, new File(codeValue));
            JudgeCode judgeCode = (JudgeCode) applicationContext.getBean("judgeCode_2");
            CompileResult compileResult = judgeCode.compile(compileParam);
            if (compileResult.isCompileSuccess()) {
                submitCodeDao.updateStatus(submitCodeId, "compile success");
                List<String> testDataPaths = testDataDao.selectTestDataPath(problemId);
                if (testDataPaths != null && !testDataPaths.isEmpty()) {
                    submitCodeDao.updateStatus(submitCodeId, "running");
                    submitCodeDao.updateTestNumber(testDataPaths.size(), submitCodeId);
                    List<RunCode> runCodes = new ArrayList<>();
                    for (int j = 0; j < testDataPaths.size(); j++) {
                        RunCode runCode = applicationContext.getBean(RunCode.class, submitCodeId, compileResult.getRunFile().getPath(), codeType, testDataPaths.get(j), j + 1);
                        runCodes.add(runCode);
                    }
                    runCodeDao.insertRunCodes(runCodes);
                }
            } else {
                submitCodeDao.updateStatusAndCompileMessage(submitCodeId, "compile fail", compileResult.getErrorMessage());
            }
        }
    }

    @Scheduled(fixedRate = 100)
    @Async
    @Override
    public void runPL() throws InterruptedException, IOException {
        Map<String, String> map = runPLQueue.pull();
        if (map != null && !map.isEmpty()) {
            int runCodeId = Integer.valueOf(String.valueOf(map.get("runCodeId")));
            String codeType = map.get("codeType");
            int submitCodeId = Integer.valueOf(String.valueOf(map.get("submitCodeId")));
            String runCodeFile = map.get("runCodeFile");
            String testDataPath = map.get("testDataPath");
            int problemId = submitCodeDao.selectProblemId(submitCodeId);
            Map<String, String> limit = problemDao.selectLimit(problemId, codeType);
            int timeLimit = Integer.valueOf(String.valueOf(limit.get("timeLimit")));
            int memoryLimit = Integer.valueOf(String.valueOf(limit.get("memoryLimit")));
            RunParam runParam = new RunParam(codeType, new File(runCodeFile), new File(testDataPath, "test.in"),
                    new File(testDataPath, "test.out"), timeLimit, memoryLimit);
            JudgeCode judgeCode = (JudgeCode) applicationContext.getBean("judgeCode_2");
            RunResult runResult = judgeCode.run(runParam);
            int runTime = 0;
            if (runResult.getRunTime() != null ) {
                runTime = Integer.valueOf(String.valueOf(runResult.getRunTime()));
            }
            int runMemory = 0;
            if (runResult.getRunMemory() != null) {
                runMemory = Integer.valueOf(String.valueOf(runResult.getRunMemory()));
            }
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
                boolean isAc = true;
                String result = "AC";
                for (String s : status) {
                    if (!"AC".equals(s)) {
                        isAc = false;
                        result = s;
                        break;
                    }
                }
                submitCodeDao.updateJudgeStatus(submitCodeId, result);
                if (isAc) {
                    problemDao.addAcceptNumber(problemId);
                }
                FileUtil.delete(runParam.getRunFile().getParentFile());
            }
        }
    }
}
