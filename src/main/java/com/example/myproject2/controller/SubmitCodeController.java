/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.controller;

import com.example.myproject2.entity.SubmitCodeListPage;
import com.example.myproject2.service.SubmitCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
        List<SubmitCodeListPage> submitCodeList = submitCodeService.getSubmitCodeList(page, limit);
        int count = submitCodeService.getSubmitCodeCount();
        map.put("page", page);
        map.put("code", 0);
        map.put("msg", null);
        map.put("count", count);
        map.put("data", submitCodeList);
        return map;
    }
}