/*
 *@author ChenCheng
 *@date 2019/10/1
 */
package com.example.myproject2.service;


import com.example.myproject2.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public void insertUser(String userEmail, String password);

    public Boolean login(String userEmail, String password);

    public int getUserId(String userEmail);

    public Boolean findUserEmailExist(String userEmail);

    public List<User> getUserList(int page, int limit);

    public int getUserNumber();

    public List<String> getUserRoles(int userId);
}
