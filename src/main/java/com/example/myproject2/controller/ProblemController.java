/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.controller;

import com.example.myproject2.entity.Problem;
import com.example.myproject2.service.ProblemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @GetMapping("/problem/{problemId}")
    public ModelAndView problem(@PathVariable("problemId") int problemId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("problemId", problemId);
        modelAndView.setViewName("problem");
        return modelAndView;
    }

    @GetMapping("/problem/data/{problemId}")
    public Problem getProblem(@PathVariable("problemId") int problemId) {
        Problem problem = problemService.getProblemByProblemId(problemId);
        return problem;
    }

    @PostMapping("/updateTestData")
    public Map<String, Object> updateTestData(@Param("problemId") int problemId, Part file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        problemService.updateTestData(problemId, file);
        return map;
    }
    @GetMapping("/downloadTestData/{problemId}")
    public void downloadTestData(@PathVariable("problemId") int problemId, HttpServletResponse response) throws IOException {
        String testDataPath = problemService.getTestDataPath(problemId);
        File file = new File(testDataPath);
        if (file.exists()) {
            //response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1 << 10];
            FileInputStream fileInputStream = new FileInputStream(testDataPath);
            int len = -1;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            fileInputStream.close();
        }
    }

    @GetMapping("/admin/problem/{problemId}")
    public ModelAndView updateProblem(@PathVariable("problemId") int problemId, ModelAndView modelAndView) {
        modelAndView.addObject("problemId", problemId);
        return new ModelAndView("admin-problem");
    }
    @GetMapping("/getTestDataMessage/{problemId}")
    public Map<String, Object> getTestDataMessage(@PathVariable("problemId") int problemId) {
        Map<String, Object> testDataMessage = problemService.getTestDataMessage(problemId);
        return testDataMessage;
    }

    @PostMapping("/updateProblem")
    public ModelAndView updateProblem(Problem problem) {
        problemService.updateProblem(problem);
        return new ModelAndView("redirect:/admin/problem/" + problem.getProblemId());
    }
}
