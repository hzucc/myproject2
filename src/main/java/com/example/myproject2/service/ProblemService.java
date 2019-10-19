/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.service;

import com.example.myproject2.entity.Problem;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProblemService {
    public boolean deleteProblem(int[] problemIds);

    public void updateTestData(int problemId, Part part) throws IOException;

    public Map<String, Object> getTestDataMessage(int problemId);

    public String getTestDataPath(int problemId);

    public int updateProblem(Problem problem);

    public Map<String, Object> getProblemLimit(int problemId);

    public int getProblemCount();

    public List<Problem> getProlemList(int page, int limit);

    public List<Map<String, Object>> getProblemList(int page, int limit);

    public Problem getProblemByProblemId(int problemId);
}
