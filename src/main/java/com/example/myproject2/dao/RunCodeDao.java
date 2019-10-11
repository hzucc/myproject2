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

    public List<Map<String, String>> selectRunCodes(int limit);

    public void updateStatus(@Param("runCodeIds") List<Integer> runCodeIds, @Param("status") String status);

    public void updateJudgeStatus(int runCodeId, String status, int runTime, int runMemory);
}
