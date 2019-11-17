/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.controller;

import com.example.myproject2.dao.SubmitCodeDao;
import com.example.myproject2.dao.UserDao;
import com.example.myproject2.entity.MemoryLimit;
import com.example.myproject2.entity.Problem;
import com.example.myproject2.entity.TimeLimit;
import com.example.myproject2.entity.UpdateTestDataMap;
import com.example.myproject2.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Time;
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
    public VoProblem2 getProblemLimit(@PathVariable("problemId") int problemId) {
        List<Object> limits = problemService.getProblemLimit(problemId);
        VoProblem2 voProblem2 = new VoProblem2((TimeLimit) limits.get(0), (MemoryLimit)limits.get(1));
        return voProblem2;
    }

    @GetMapping("/problem/data/{problemId}")
    public VoProblem3 getProblem(@PathVariable("problemId") int problemId) {
        Problem problem = problemService.getProblem(problemId);
        VoProblem3 voProblem3 = new VoProblem3(problem);
        return voProblem3;
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
    private class VoProblem1 {
        private int problemId;
        private String problemName;
        private short acceptNum;
        private short submitNum;
        private String status;

        @Override
        public String toString() {
            return "VoProblem1{" +
                    "problemId=" + problemId +
                    ", problemName='" + problemName + '\'' +
                    ", acceptNum=" + acceptNum +
                    ", submitNum=" + submitNum +
                    ", status='" + status + '\'' +
                    '}';
        }

        public VoProblem1(Problem problem) {
            problemId = problem.getProblemId();
            problemName = problem.getProblemName();
            acceptNum = problem.getAcceptNum();
            submitNum = problem.getSubmitNum();
        }

        public int getProblemId() {
            return problemId;
        }

        public void setProblemId(int problemId) {
            this.problemId = problemId;
        }

        public String getProblemName() {
            return problemName;
        }

        public void setProblemName(String problemName) {
            this.problemName = problemName;
        }

        public short getAcceptNum() {
            return acceptNum;
        }

        public void setAcceptNum(short acceptNum) {
            this.acceptNum = acceptNum;
        }

        public short getSubmitNum() {
            return submitNum;
        }

        public void setSubmitNum(short submitNum) {
            this.submitNum = submitNum;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    private class VoProblem2 {
        private TimeLimit timeLimit;
        private MemoryLimit memoryLimit;

        @Override
        public String toString() {
            return "VoProblem2{" +
                    "timeLimit=" + timeLimit +
                    ", memoryLimit=" + memoryLimit +
                    '}';
        }

        public VoProblem2(TimeLimit timeLimit, MemoryLimit memoryLimit) {
            this.timeLimit = timeLimit;
            this.memoryLimit = memoryLimit;
        }

        public TimeLimit getTimeLimit() {
            return timeLimit;
        }

        public void setTimeLimit(TimeLimit timeLimit) {
            this.timeLimit = timeLimit;
        }

        public MemoryLimit getMemoryLimit() {
            return memoryLimit;
        }

        public void setMemoryLimit(MemoryLimit memoryLimit) {
            this.memoryLimit = memoryLimit;
        }
    }

    private class VoProblem3 {
        private String problemName;
        private String problemContent;
        private TimeLimit timeLimit;
        private MemoryLimit memoryLimit;

        @Override
        public String toString() {
            return "VoProblem3{" +
                    "problemName='" + problemName + '\'' +
                    ", problemContent='" + problemContent + '\'' +
                    ", timeLimit=" + timeLimit +
                    ", memoryLimit=" + memoryLimit +
                    '}';
        }

        public VoProblem3(Problem problem) {
            problemName = problem.getProblemName();
            problemContent = problem.getProblemContent();
            timeLimit = problem.getTimeLimit();
            memoryLimit = problem.getMemoryLimit();
        }

        public String getProblemName() {
            return problemName;
        }

        public void setProblemName(String problemName) {
            this.problemName = problemName;
        }

        public String getProblemContent() {
            return problemContent;
        }

        public void setProblemContent(String problemContent) {
            this.problemContent = problemContent;
        }

        public TimeLimit getTimeLimit() {
            return timeLimit;
        }

        public void setTimeLimit(TimeLimit timeLimit) {
            this.timeLimit = timeLimit;
        }

        public MemoryLimit getMemoryLimit() {
            return memoryLimit;
        }

        public void setMemoryLimit(MemoryLimit memoryLimit) {
            this.memoryLimit = memoryLimit;
        }
    }
}

