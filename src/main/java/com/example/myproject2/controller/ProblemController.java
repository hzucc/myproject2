/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.controller;

import com.example.myproject2.dao.ProblemDao;
import com.example.myproject2.dao.SubmitCodeDao;
import com.example.myproject2.dao.UserDao;
import com.example.myproject2.entity.Problem;
import com.example.myproject2.entity.UpdateTestDataMap;
import com.example.myproject2.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private UpdateTestDataMap updateTestDataMap;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SubmitCodeDao submitCodeDao;

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

    @PostMapping("/problem_list")
    public Map<String, Object> problomList(int page, int limit) {
        Map<String, Object> map = new HashMap<>();
        List<Problem> problems = problemService.getProlemList(1, 20);
        int count = problemService.getProblemCount();
        map.put("page", page);
        map.put("code", 0);
        map.put("msg", null);
        map.put("count", count);
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userEmail = userDetails.getUsername();
            int userId = userDao.selectUserId(userEmail);
            List<VoProblem1> problem1s = new ArrayList<>();
            for (Problem problem: problems) {
                VoProblem1 problem1 = new VoProblem1(problem);
                boolean isAC = submitCodeDao.selectUserAcceptProblem(userId, problem.getProblemId());
                if (isAC) {
                    problem1.setStatus("AC");
                }
                problem1s.add(problem1);
            }
            map.put("data", problem1s);
        } catch (Exception e) {
            map.put("data", problems);
        }
        return map;
    }
}
class VoProblem1 {
    private int problemId;
    private String problemName;
    private int acceptNumber;
    private int submitNumber;
    private String status;
    public VoProblem1(Problem problem) {
        problemId = problem.getProblemId();
        problemName = problem.getProblemName();
        acceptNumber = problem.getAcceptNumber();
        submitNumber = problem.getSubmitNumber();
    }
    public void setStatus(String status) {
        this.status = status;
    }
}