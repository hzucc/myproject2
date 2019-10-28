/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.service;

import com.example.myproject2.entity.SubmitCode;
import com.example.myproject2.entity.SubmitCodeListPage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SubmitCodeService {
    public void addSubmitCode(SubmitCode submitCode) throws IOException;

    public List<Map<String, String>> getSubmitCodeList(int page, int limit);

    public int getSubmitCodeCount();

    public Map<String, String> getSubmitCode(String userEmail, int problemId) throws IOException;

}
