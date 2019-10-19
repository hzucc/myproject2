/*
 *@author ChenCheng
 *@date 2019/10/19
 */
package com.example.myproject2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProblemListController {
    @GetMapping("/problem_list")
    public ModelAndView problemList() {
        return new ModelAndView("problem-list");
    }
}
