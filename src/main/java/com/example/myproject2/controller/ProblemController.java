/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.controller;

import com.example.myproject2.entity.Problem;
import com.example.myproject2.entity.UpdateTestDataMap;
import com.example.myproject2.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private UpdateTestDataMap updateTestDataMap;

    @GetMapping("/problem/{problemId}")
    public ModelAndView problem(@PathVariable("problemId") int problemId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("problemId", problemId);
        modelAndView.setViewName("problem");
        return modelAndView;
    }

    @GetMapping("/problem_limit/{problemId}")
    public Map<String, Object> getProblemLimit(@PathVariable("problemId") int problemId) {
        Map<String, Object> map = problemService.getProblemLimit(problemId);
        return map;
    }

    @GetMapping("/problem/data/{problemId}")
    public Problem getProblem(@PathVariable("problemId") int problemId) {
        Problem problem = problemService.getProblemByProblemId(problemId);
        return problem;
    }






}
