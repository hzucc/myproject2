/*
 *@author ChenCheng
 *@date 2019/9/30
 */
package com.example.myproject2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetDataController {
    @GetMapping("/getData")
    public String getData() {
        return "test-Datat";
    }
}
