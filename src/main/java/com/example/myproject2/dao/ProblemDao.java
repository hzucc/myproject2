/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.dao;

import com.example.myproject2.entity.MemoryLimit;
import com.example.myproject2.entity.Problem;
import com.example.myproject2.entity.TimeLimit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProblemDao {

    public void insertProblem(Problem problem);

    public void deleteProblem(int problemId);

    public void updateTestDataPath(int problemId, String testDataPath);

    public void updateProblem(Problem problem);

    public void updateSubmitNum(int problemId);

    public void updateAcceptNum(int problemId);

    public boolean selectProblemId(int problemId);

    public int selectProblemIdByProblemName(String problemName);

    public List<Problem> selectProblemList(int page, int limit);

    public List<Problem> selectProblemIdAndProblemNameList(int page, int limit);

    public Problem selectProblemByProblemId(int problemId);

    /**
     * 根据题目id，查询problemName, problemContent并包装进一个Problem
     * @param problemId 题目id
     * @return Probmem
     */
    public Problem selectProblem2ByProblemId(int problemId);

    public String selectTestDataPath(int problemId);

}
