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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class IndexController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/problemList")
    public List<Problem> problomList() {
        List<Problem> problems = problemService.getProlemList(1, 20);
        return problems;
    }
    @GetMapping("/isAdmin")
    public boolean isAdmin(HttpServletRequest request) {
        boolean isAdmin = false;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user = applicationContext.getBean(User.class);
        user.setUserId(1);
        user.setUserPassword("78946");

        if (userService.loginUserIsAdministrators(user)) {
            isAdmin = true;
        }
        return isAdmin;
    }
}
