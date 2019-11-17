/*
 *@author ChenCheng
 *@date 2019/10/16
 */
package com.example.myproject2.controller;

import com.example.myproject2.entity.MemoryLimit;
import com.example.myproject2.entity.Problem;
import com.example.myproject2.entity.TimeLimit;
import com.example.myproject2.entity.UpdateTestDataMap;
import com.example.myproject2.service.ProblemService;
import com.example.myproject2.util.MyJsonObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminProblemController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private UpdateTestDataMap updateTestDataMap;

    @GetMapping(value = {"/admin/problem", "/admin/problem/{problemId}"})
    public ModelAndView updateProblem(@PathVariable(value = "problemId", required = false) Integer problemId, ModelAndView modelAndView) {
        modelAndView.addObject("problemId", problemId);
        return new ModelAndView("admin-problem");
    }

    @PostMapping("/admin/problem_update")
    public int updateProblem(String problem) throws NoSuchMethodException, MyJsonObject.NotSupportTypeException, IllegalAccessException, InstantiationException, MyJsonObject.JsonFormatException, InvocationTargetException {
        MyJsonObject myJsonObject = new MyJsonObject();
        Problem problem1 = (Problem) myJsonObject.toPojo(problem, Problem.class);
        return problemService.updateProblem(problem1);
    }

    @PostMapping("/admin/test_data_update")
    public Map<String, Object> updateTestData(@Param("problemId") int problemId, Part file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        if (updateTestDataMap.contains(problemId)) {
            map.put("submitError", "提交失败,等待旧的数据包提交中");
        } else {
            problemService.updateTestData(problemId, file);
        }
        return map;
    }
    @GetMapping("/admin/test_data_download/{problemId}")
    public void downloadTestData(@PathVariable("problemId") int problemId, HttpServletResponse response) throws IOException {
        String testDataPath = problemService.getTestDataPath(problemId);
        File file = new File(testDataPath);
        if (file.exists()) {
            response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1 << 10];
            FileInputStream fileInputStream = new FileInputStream(testDataPath);
            int len = -1;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            fileInputStream.close();
        }
    }

    @GetMapping("/admin/test_data_message/{problemId}")
    public Map<String, Object> getTestDataMessage(@PathVariable("problemId") int problemId) {
        Map<String, Object> testDataMessage = problemService.getTestDataMessage(problemId);
        return testDataMessage;
    }

}
