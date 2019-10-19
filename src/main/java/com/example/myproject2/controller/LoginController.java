/*
 *@author ChenCheng
 *@date 2019/10/15
 */
package com.example.myproject2.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {
    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
         modelAndView.setViewName("login");
        return modelAndView;
    }
}
