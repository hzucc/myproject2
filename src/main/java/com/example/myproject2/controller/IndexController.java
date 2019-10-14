/*
 *@author ChenCheng
 *@date 2019/9/25
 */
package com.example.myproject2.controller;

import com.example.myproject2.entity.Problem;
import com.example.myproject2.entity.User;
import com.example.myproject2.service.ProblemService;
import com.example.myproject2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request, ModelAndView modelAndView) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean isAdmin = userService.loginUserIsAdministrators(user);
        modelAndView.addObject("isAdmin", isAdmin);
        return new ModelAndView("index");
    }

    @PostMapping("/problemList")
    public Map<String, Object> problomList(int page, int limit) {
        Map<String, Object> map = new HashMap<>();
        List<Problem> problems = problemService.getProlemList(1, 20);
        int count = problemService.getProblemCount();
        map.put("code", 0);
        map.put("msg", null);
        map.put("count", count);
        map.put("data", problems);
        return map;
    }

    @GetMapping("/isAdmin")
    public boolean isAdmin(HttpServletRequest request) {
        boolean isAdmin = false;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (userService.loginUserIsAdministrators(user)) {
            isAdmin = true;
        }
        return isAdmin;
    }
}
