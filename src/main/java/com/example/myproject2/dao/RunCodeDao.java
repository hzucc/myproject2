/*
 *@author ChenCheng
 *@date 2019/10/9
 */
package com.example.myproject2.dao;

import com.example.myproject2.entity.RunCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RunCodeDao {
    public void insertRunCodes(@Param("runCodes") List<RunCode> runCodes);

    public void updateStatus(int runCodeId, String status);

    public int updateStatus_WaitingToRunning(int runCodeId);

    public void updateJudgeStatus(int runCodeId, String status, short runTime, short runMemory);

    public RunCode selectRunCodeInWaiting();

    public String selectJudgeResult(int submitCodeId);

}
