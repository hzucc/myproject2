/*
 *@author ChenCheng
 *@date 2019/11/6
 */
package com.example.myproject2.service.impl;

import com.example.myproject2.dao.RunCodeDao;
import com.example.myproject2.dao.SubmitCodeDao;
import com.example.myproject2.entity.RunCode;
import com.example.myproject2.service.RunCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RunCodeServiceImpl implements RunCodeService {
    @Autowired
    private RunCodeDao runCodeDao;
    @Autowired
    private SubmitCodeDao submitCodeDao;

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    @Override
    public RunCode runCodePull() {
        RunCode runCode = runCodeDao.selectRunCodeInWaiting();
        if (runCode != null) {
            int runCodeId = runCode.getRunCodeId();
            int num = runCodeDao.updateStatus_WaitingToRunning(runCodeId);
            boolean isUpdateStatus = num == 1? true: false;
            if (!isUpdateStatus) {
                runCode = null;
            }
        }
        return runCode;
    }

}
