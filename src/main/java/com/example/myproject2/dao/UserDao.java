/*
 *@author ChenCheng
 *@date 2019/10/1
 */
package com.example.myproject2.dao;

import com.example.myproject2.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    public List<User> selectUsers(int page, int limit);

    public List<String> selectRoleListByUserId(int userId);

    public boolean selectUserEmailExist(String userEmail);

    public void insertUser(String userEmail, String password);

    public boolean selectUserExist(String userEmail, String password);

    public int selectUserId(String userEmail);

    public String selectPasswordByUserEmail(String userEmail);

    public List<String> selectRoleListByUserEmail(String userEmail);
}
