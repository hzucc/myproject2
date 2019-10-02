/*
 *@author ChenCheng
 *@date 2019/10/1
 */
package com.example.myproject2.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public int getUserRights(int userId, String userPassword);
}
