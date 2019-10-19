/*
 *@author ChenCheng
 *@date 2019/10/18
 */
package com.example.myproject2.controller;

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
        List<Map<String, Object>> problemList = problemService.getProblemList(page, limit);
        map.put("code", 0);
        map.put("msg", null);
        map.put("count", count);
        map.put("data", problemList);
        return map;
    }
    @GetMapping("/admin/user_list")
    public Map<String, Object> getUesrList(int page, int limit) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> userList = userService.getUserList(page, limit);
        int count = userService.getUserNumber();
        map.put("code", 0);
        map.put("msg", null);
        map.put("count", count);
        map.put("data", userList);
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
}
