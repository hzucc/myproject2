/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.dao;

import com.example.myproject2.entity.SubmitCode;
import com.example.myproject2.entity.SubmitCodeListPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitCodeDao {
    public void insertSubmitCode(SubmitCode submitCode);

    public List<SubmitCodeListPage> selectSubmitCodeList(int page, int limit);

}
