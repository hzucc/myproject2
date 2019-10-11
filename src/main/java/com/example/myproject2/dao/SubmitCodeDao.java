/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.dao;

import com.example.myproject2.entity.SubmitCode;
import com.example.myproject2.entity.SubmitCodeListPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SubmitCodeDao {
    public void insertSubmitCode(SubmitCode submitCode);

    public List<SubmitCodeListPage> selectSubmitCodeList(int page, int limit);

    public List<Map<String, String>> selectCodeInWaiting(int limit);

    public void updateStatusList(@Param("submitCodeIds") List<Integer> submitCodeIds, @Param("status") String status);

    public void updateStatus(int submitCodeId, String status);

    public void updateTestNumber(int testNumber, int submitCodeId);

    public void updateJudgeTestNumber(int submitCodeId);

    public void updateJudgeStatus(int submitCodeId, String judgeStatus);

    public int selectProblemId(int submitCodeId);

    public int selectJudgeTestNumber(int submitCodeId);

    public int selectTestNumber(int submitCodeId);

    public List<String> selectJudgeStatus(int submitCodeId);


}
