/*
 *@author ChenCheng
 *@date 2019/10/1
 */
package com.example.myproject2.service;

import com.example.myproject2.entity.User;

public interface UserService {
    public boolean loginUserIsAdministrators(User loginUser);
}
