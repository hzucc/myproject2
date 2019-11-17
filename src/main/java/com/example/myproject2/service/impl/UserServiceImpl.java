/*
 *@author ChenCheng
 *@date 2019/10/1
 */
package com.example.myproject2.service.impl;

import com.example.myproject2.dao.TableCountDao;
import com.example.myproject2.dao.UserDao;
import com.example.myproject2.dao.User_RoleDao;
import com.example.myproject2.entity.User;
import com.example.myproject2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private User_RoleDao user_roleDao;
    @Autowired
    private TableCountDao tableCountDao;

    @Transactional
    @Override
    public void insertUser(String userEmail, String password) {
        userDao.insertUser(userEmail, password);
        user_roleDao.insertUser_Role(userEmail, "ROLE_USER");
    }

    @Override
    public Boolean login(String userEmail, String password) {
        boolean loginSuccess = userDao.selectUserExist(userEmail, password);
        return loginSuccess;
    }

    @Override
    public int getUserId(String userEmail) {
        int userId = userDao.selectUserId(userEmail);
        return userId;
    }

    @Override
    public Boolean findUserEmailExist(String userEmail) {
        boolean userEmailExist = false;
        if (userDao.selectUserEmailExist(userEmail)) {
            userEmailExist = true;
        }
        return userEmailExist;
    }

    @Override
    public List<User> getUserList(int page, int limit) {
        List<User> users = userDao.selectUsers((page - 1) * limit, limit);
        return users;

    }

    @Override
    public int getUserNumber() {
        return tableCountDao.selectTableCount("user");
    }

    @Override
    public List<String> getUserRoles(int userId) {
        List<String> roles = userDao.selectRoleListByUserId(userId);
        return roles;
    }
}



