/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.service;

import com.example.myproject2.entity.Problem;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ProblemService {
    public boolean deleteProblem(int[] problemIds);

    public void updateTestData(int problemId, Part part) throws IOException;

    public Map<String, Object> getTestDataMessage(int problemId) throws IOException;

    public String getTestDataPath(int problemId);

    public int updateProblem(Problem problem);

    public List<Object> getProblemLimit(int problemId);

    public List<Short> getProblemLimit(int problemId, String codeType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    public int getProblemCount();

    public List<Problem> getProlemList(int page, int limit);

    public List<Problem> getProblemIdAndProblemNameList(int page, int limit);

    public Problem getProblemByProblemId(int problemId);

    /**
     * 根据题目id，获得problemName, problemContent, timeLimit, memoryLimit并包装进Problem
     * @param problemId 题目id
     * @return Problem
     */
    public Problem getProblem(int problemId);
}
