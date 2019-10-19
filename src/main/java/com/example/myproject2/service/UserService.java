/*
 *@author ChenCheng
 *@date 2019/10/1
 */
package com.example.myproject2.service;


import java.util.List;
import java.util.Map;

public interface UserService {
    public void insertUser(String userEmail, String password);

    public Boolean login(String userEmail, String password);

    public int getUserId(String userEmail);

    public Boolean findUserEmailExist(String userEmail);

    public List<Map<String, Object>> getUserList(int page, int limit);

    public int getUserNumber();
}
