/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.service;

import com.example.myproject2.entity.SubmitCode;
import com.example.myproject2.entity.SubmitCodeListPage;

import java.util.List;

public interface SubmitCodeService {
    public void addSubmitCode(SubmitCode submitCode);

    public List<SubmitCodeListPage> getSubmitCodeList(int page, int limit);

    public int getSubmitCodeCount();
}
