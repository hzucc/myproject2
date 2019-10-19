/*
 *@author ChenCheng
 *@date 2019/10/17
 */
package com.example.myproject2.controller;

import com.example.myproject2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public boolean register(String userEmail, String password) {
        boolean success = true;
        try{
            userService.insertUser(userEmail, password);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        } finally {
            return success;
        }
    }
    @GetMapping("/user_email_exist/{userEmail}")
    public Boolean getUserEmailExist(@PathVariable("userEmail") String userEmail) {
        boolean userEmailExist = false;
        if (userService.findUserEmailExist(userEmail)) {
            userEmailExist = true;
        }
        return userEmailExist;
    }
}
