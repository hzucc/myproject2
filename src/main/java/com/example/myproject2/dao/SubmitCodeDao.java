/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.dao;

import com.example.myproject2.dao.ResultEntity.SubmitCode1;
import com.example.myproject2.entity.SubmitCode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitCodeDao {
    public void insertSubmitCode(SubmitCode submitCode);

    public int updateStatus(int submitCodeId, String status);

    public int updateStatus_WaitingToCompiling(int submitCodeId);

    public void updateStatusAndCompileMessage(int submitCodeId, String status, String compileMessage);

    public void updateJudgeTestNum(int submitCodeId);

    public void updateStatusAndTestNum(int submitCodeId, String status, short testNum);

    public int selectProblemId(int submitCodeId);

    public SubmitCode selectJudgeTestNumAndTestNum(int submitCodeId);

    public List<SubmitCode> selectSubmitCodeListOfUser(int userId, int problemId, int limit);

    public boolean selectUserAcceptProblem(int userId, int problemId);

    public List<SubmitCode1> selectSubmitCodeList(int page, int limit);

    public SubmitCode selectSubmitCodeInWaiting();

}
