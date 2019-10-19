/*
 *@author ChenCheng
 *@date 2019/10/18
 */
package com.example.myproject2.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface User_RoleDao {
    public void insertUser_Role(String userEmail, String roleName);
}
