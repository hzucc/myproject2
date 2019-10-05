/*
 *@author ChenCheng
 *@date 2019/10/1
 */
package com.example.myproject2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {
    @GetMapping("/test")
    public ModelAndView test() {
        return new ModelAndView("test");
    }

    @GetMapping("/testData")
    public String testData() {

        return "你好";
    }
}