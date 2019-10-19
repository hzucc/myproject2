/*
 *@author ChenCheng
 *@date 2019/10/3
 */
package com.example.myproject2.controller;

import com.example.myproject2.entity.SubmitCode;
import com.example.myproject2.entity.User;
import com.example.myproject2.service.ProblemService;
import com.example.myproject2.service.SubmitCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class AceEditorController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private SubmitCodeService submitCodeService;

    @GetMapping("/ace-editor/{problemId}")
    public ModelAndView aceEditor(@PathVariable(value = "problemId", required = false) int problemId, ModelAndView modelAndView) {
        modelAndView.addObject("problemId", problemId);
        modelAndView.setViewName("ace-editor");
        return modelAndView;
    }

    @PostMapping("/submit_code")
    public void judgeCode(SubmitCode submitCode, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            submitCode.setUserId(user.getUserId());
        }
        submitCodeService.addSubmitCode(submitCode);
    }
}
