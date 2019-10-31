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

    public void updateProbelm(Problem problem);

    public void addSubmitNumber(int problemId);

    public void addAcceptNumber(int problemId);

    public boolean selectProblemId(int problemId);

    public int selectProblemIdByProblemName(String problemName);

    public Map<String, String> selectLimit(int problemId, String codeType);

    public List<Problem> selectProblemList(int page, int limit);

    public List<Map<String, Object>> selectProblemIdAndProblemNameList(int page, int limit);

    public Problem selectProblemByProblemId(int problemId);

    public void setLimit(@Param("timeLimit") TimeLimit timeLimit,@Param("memoryLimit") MemoryLimit memoryLimit,@Param("problemId") int problemId);

    public String selectTestDataPath(int problemId);


}
