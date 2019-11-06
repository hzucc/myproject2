/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.controller;

import com.example.myproject2.dao.SubmitCodeDaoResult.SubmitCode1;
import com.example.myproject2.entity.SubmitCode;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SubmitCodeController {
    @Autowired
    private SubmitCodeService submitCodeService;
    @GetMapping("/submit_code_list")
    public ModelAndView submitList() {
        return new ModelAndView("submit-code-list");
    }

    @PostMapping("/submit_code_list")
    public Map<String, Object> submitCodeList(int page, int limit) {
        Map<String, Object> map = new HashMap<>();
        List<SubmitCode1> submitCodes = submitCodeService.getSubmitCodeList(page, limit);
        int count = submitCodeService.getSubmitCodeCount();
        map.put("page", page);
        map.put("code", 0);
        map.put("msg", null);
        map.put("count", count);
        map.put("data", submitCodes);
        return map;
    }

    @GetMapping("/submit_code/{problemId}")
    public Map<String, Object> getSubmitCode(@PathVariable("problemId") int problemId) throws IOException {
        Map<String, Object> map = new HashMap<>();
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userName = userDetails.getUsername();
            SubmitCode submitCode = submitCodeService.getSubmitCode(userName, problemId);

            map.put("submitCode", submitCode);
            map.put("isLogin", true);
        } catch (Exception e) {
            map.put("isLogin", false);
        } finally {
            return map;
        }
    }
}