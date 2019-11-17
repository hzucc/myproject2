/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.service.impl;

import com.example.myproject2.dao.*;
import com.example.myproject2.dao.ResultEntity.SubmitCode1;
import com.example.myproject2.entity.CompileSuffixMap;
import com.example.myproject2.entity.SubmitCode;
import com.example.myproject2.service.SubmitCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class SubmitCodeServiceImpl implements SubmitCodeService {
    @Value("${myproject2.compile}")
    private String compileFilepath;
    @Autowired
    private SubmitCodeDao submitCodeDao;
    @Autowired
    private TableCountDao tableCountDao;
    @Autowired
    private CompileSuffixMap compileSuffixMap;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private RunCodeDao runCodeDao;

    @Transactional
    @Override
    public void addSubmitCode(SubmitCode submitCode) throws IOException {
        String codeValue = submitCode.getCodeValue();
        String compileSuffixName = compileSuffixMap.handleType(submitCode.getCodeType());
        File workFile = new File(compileFilepath, UUID.randomUUID().toString());
        workFile.mkdir();
        File compileFile = new File(workFile.getPath(), "Main" + compileSuffixName);
        compileFile.createNewFile();
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(compileFile)));
        printWriter.write(codeValue);
        printWriter.close();
        submitCode.setCodeValue(compileFile.getPath());
        problemDao.updateSubmitNum(submitCode.getProblemId());
        submitCodeDao.insertSubmitCode(submitCode);
    }

    @Override
    public List<SubmitCode1> getSubmitCodeList(int page, int limit) {
        List<SubmitCode1> submitCodeListPages = submitCodeDao.selectSubmitCodeList((page - 1) * limit, limit);
        return submitCodeListPages;
    }

    @Override
    public int getSubmitCodeCount() {
        return tableCountDao.selectTableCount("submit_code");
    }

    @Override
    public SubmitCode getSubmitCode(String userEmail, int problemId) throws IOException {
        int userId = userDao.selectUserId(userEmail);
        List<SubmitCode> submitCodes = submitCodeDao.selectSubmitCodeListOfUser(userId, problemId, 1);
        if (!submitCodes.isEmpty()) {
            SubmitCode submitCode = submitCodes.get(0);
            String codeValue = submitCode.getCodeValue();
            File file = new File(codeValue);
            BufferedReader buf = new BufferedReader(new FileReader(codeValue));
            StringBuilder res = new StringBuilder();
            String str = null;
            while ((str = buf.readLine()) != null) {
                res.append(str + '\n');
            }
            submitCode.setCodeValue(res.toString());
            return submitCode;
        } else {
            return null;
        }
    }

    @Override
    public SubmitCode submitCodePull() {
        SubmitCode submitCode = submitCodeDao.selectSubmitCodeInWaiting();
        if (submitCode != null) {
            int submitCodeId = submitCode.getSubmitCodeId();
            int num = submitCodeDao.updateStatus_WaitingToCompiling(submitCodeId);
            boolean isUpdateStatus = num == 1? true: false;
            if (!isUpdateStatus) {
                submitCode = null;
            }
        }
        return submitCode;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    @Override
    public String setJudgeTestNum(int submitCodeId, int runCodeId, String result, short runTime, short runMemory) {
        runCodeDao.updateJudgeStatus(runCodeId, result, runTime, runMemory);
        submitCodeDao.updateJudgeTestNum(submitCodeId);
        SubmitCode submitCode = submitCodeDao.selectJudgeTestNumAndTestNum(submitCodeId);
        int judgeNum = submitCode.getJudgeTestNum();
        int testNum = submitCode.getTestNum();
        boolean isJudgeCompletion = judgeNum == testNum;
        String res;
        if (isJudgeCompletion) {
            res = runCodeDao.selectJudgeResult(submitCodeId);
            if (res == null) {
                res  = "AC";
            }
        } else {
            res = "running";
        }
        return res;
    }
}
