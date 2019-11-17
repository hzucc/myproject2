/*
 *@author ChenCheng
 *@date 2019/10/18
 */
package com.example.myproject2.controller;

import com.example.myproject2.entity.Problem;
import com.example.myproject2.entity.User;
import com.example.myproject2.service.ProblemService;
import com.example.myproject2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private UserService userService;
    @GetMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("admin");
    }
    @GetMapping("/admin/problem_list")
    public Map<String, Object> getProblemList(int page, int limit) {
        Map<String, Object> map = new HashMap<>();
        int count = problemService.getProblemCount();
        List<Problem> problems = problemService.getProblemIdAndProblemNameList(page, limit);
        List<VoProblem2> problem2s = new ArrayList<>();
        for (Problem problem: problems) {
            VoProblem2 voProblem2 = new VoProblem2(problem);
            problem2s.add(voProblem2);
        }
        map.put("code", 0);
        map.put("msg", null);
        map.put("count", count);
        map.put("data", problem2s);
        return map;
    }
    @GetMapping("/admin/user_list")
    public Map<String, Object> getUesrList(int page, int limit) {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userService.getUserList(page, limit);
        List<VoUser1> voUser1s = new ArrayList<>();
        for (User user: users) {
            List<String> roles = userService.getUserRoles(user.getUserId());
            VoUser1 voUser1 = new VoUser1(user, roles);
            voUser1s.add(voUser1);
        }
        int count = userService.getUserNumber();
        map.put("code", 0);
        map.put("msg", null);
        map.put("count", count);
        map.put("data", voUser1s);
        return map;
    }

    @DeleteMapping("/admin_delete/problem/{problemIds}")
    public String deleteProblem(@PathVariable("problemIds") int[] problemIds) {
        boolean success = false;
        if (problemService.deleteProblem(problemIds)) {
            success = false;
        }
        return success?"ok":"error";
    }
    private class VoProblem2{
        private int problemId;
        private String problemName;
        public VoProblem2(Problem problem) {
            this.problemId = problem.getProblemId();
            this.problemName = problem.getProblemName();
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

        @Override
        public String toString() {
            return "VoProblem2{" +
                    "problemId=" + problemId +
                    ", problemName='" + problemName + '\'' +
                    '}';
        }
    }

    private class VoUser1{
        private int userId;
        private String userEmail;
        private String userName;
        private List<String> roles;
        public VoUser1(User user, List<String> roles) {
            this.userId = user.getUserId();
            this.userEmail = user.getUserEmail();
            this.userName = user.getUserName();
            this.roles = roles;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        @Override
        public String toString() {
            return "VoUser1{" +
                    "userId=" + userId +
                    ", userEmail='" + userEmail + '\'' +
                    ", userName='" + userName + '\'' +
                    ", roles=" + roles +
                    '}';
        }
    }

}






