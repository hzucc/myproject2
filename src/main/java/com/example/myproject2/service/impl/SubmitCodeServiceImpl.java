/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.service.impl;

import com.example.myproject2.dao.SubmitCodeDao;
import com.example.myproject2.dao.TableCountDao;
import com.example.myproject2.entity.SubmitCode;
import com.example.myproject2.entity.SubmitCodeListPage;
import com.example.myproject2.service.SubmitCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmitCodeServiceImpl implements SubmitCodeService {
    @Autowired
    private SubmitCodeDao submitCodeDao;
    @Autowired
    private TableCountDao tableCountDao;
    @Override
    public void addSubmitCode(SubmitCode submitCode) {
        submitCodeDao.insertSubmitCode(submitCode);
    }

    @Override
    public List<SubmitCodeListPage> getSubmitCodeList(int page, int limit) {
        List<SubmitCodeListPage> submitCodeListPages = submitCodeDao.selectSubmitCodeList((page - 1) * limit, limit);
        return submitCodeListPages;
    }

    @Override
    public int getSubmitCodeCount() {
        return tableCountDao.selectTableCount("submit_code");
    }
}
