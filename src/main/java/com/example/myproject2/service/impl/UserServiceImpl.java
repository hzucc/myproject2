/*
 *@author ChenCheng
 *@date 2019/10/1
 */
package com.example.myproject2.service.impl;

import com.example.myproject2.dao.UserDao;
import com.example.myproject2.entity.User;
import com.example.myproject2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public boolean loginUserIsAdministrators(User loginUser) {
        boolean isAdmin = false;
        if (loginUser != null && userDao.getUserRights(loginUser.getUserId(), loginUser.getUserPassword()) > 99) {
            isAdmin = true;
        }
        return isAdmin;
    }
}
