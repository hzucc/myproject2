/*
 *@author ChenCheng
 *@date 2019/10/5
 */
package com.example.myproject2.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface TableCountDao {
    public int selectTableCount(String tableName);
}
