/*
 *@author ChenCheng
 *@date 2019/10/3
 */
package com.example.myproject2.controller;

import com.example.myproject2.dao.UserDao;
import com.example.myproject2.entity.SubmitCode;
import com.example.myproject2.service.ProblemService;
import com.example.myproject2.service.SubmitCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;

@RestController
public class AceEditorController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private SubmitCodeService submitCodeService;
    @Autowired
    private UserDao userDao;

    @GetMapping("/ace-editor/{problemId}")
    public ModelAndView aceEditor(@PathVariable(value = "problemId", required = false) int problemId, ModelAndView modelAndView) {
        modelAndView.addObject("problemId", problemId);
        modelAndView.setViewName("ace-editor");
        return modelAndView;
    }

    @PostMapping("/submit_code")
    public void judgeCode(SubmitCode submitCode) throws IOException {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userEmail = userDetails.getUsername();
            int userId = userDao.selectUserId(userEmail);
            submitCode.setUserId(userId);
        } catch (Exception e) {

        } finally {
            submitCodeService.addSubmitCode(submitCode);
        }
    }
}
