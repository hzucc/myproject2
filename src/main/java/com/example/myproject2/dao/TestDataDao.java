/*
 *@author ChenCheng
 *@date 2019/10/2
 */
package com.example.myproject2.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDataDao {
    public void insertTestData(@Param("problemId") int problemId,@Param("testDataPaths") List<String> testDataPaths);

    public void deleteTestData(int problemId);
}
