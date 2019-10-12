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

    public List<Map<String, String>> selectSubmitCodeList(int page, int limit);

    public Map<String, String> selectSubmitCodeInWaiting();

    public void updateStatus(int submitCodeId, String status);

    public void updateTestNumber(int testNumber, int submitCodeId);

    public void updateJudgeTestNumber(int submitCodeId);

    public void updateJudgeStatus(int submitCodeId, String judgeStatus);

    public int selectProblemId(int submitCodeId);

    public int selectJudgeTestNumber(int submitCodeId);

    public int selectTestNumber(int submitCodeId);

    public List<String> selectJudgeStatus(int submitCodeId);


}
