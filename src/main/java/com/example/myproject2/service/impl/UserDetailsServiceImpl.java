/*
 *@author ChenCheng
 *@date 2019/10/15
 */
package com.example.myproject2.service.impl;

import com.example.myproject2.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        String password = userDao.selectPasswordByUserEmail(userEmail);
        List<String> roles = userDao.selectRoleListByUserEmail(userEmail);
        return changeToUser(userEmail, password, roles);
    }

    private UserDetails changeToUser(String userEmail, String password, List<String> roles) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (String role : roles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            authorityList.add(grantedAuthority);
        }
        UserDetails userDetails = new User(userEmail, new BCryptPasswordEncoder().encode(password), authorityList);
        return userDetails;
    }
}
