/*
 *@author ChenCheng
 *@date 20-4-4
 */
package com.example.myproject2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CompetitionController {
    @GetMapping("/competition_list")
    public ModelAndView competition_list(ModelAndView modelAndView) {
        modelAndView.setViewName("competition-list");
        return modelAndView;
    }

}
