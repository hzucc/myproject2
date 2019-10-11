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
    public List<Problem> selectProblemList(int page, int limit);

    public Problem selectProblemByProblemId(int problemId);

    public void insertProblem(Problem problem);

    public void setLimit(@Param("timeLimit") TimeLimit timeLimit,@Param("memoryLimit") MemoryLimit memoryLimit,@Param("problemId") int problemId);

    public String selectTestDataPath(int problemId);

    public void updateTestDataPath(int problemId, String testDataPath);

    public void updateProbelm(Problem problem);

    public boolean selectProblemId(int problemId);

    public int selectProblemIdByProblemName(String problemName);

    public Map<String, String> selectLimit(int problemId, String codeType);

}
