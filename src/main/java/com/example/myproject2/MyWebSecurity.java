/*
 *@author ChenCheng
 *@date 2019/10/15
 */
package com.example.myproject2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@EnableWebSecurity
public class MyWebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //设置用户密码服务和密码编辑器
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
    @Bean
    public JdbcTokenRepositoryImpl persistentRememberMeToken() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ADMIN_LEVEL1') or hasRole('ADMIN_SUPER')")
                .antMatchers("/admin_delete/**").access("hasRole('ADMIN_LEVEL2') or hasRole('ADMIN_SUPER')")
                //记住我功能
                .and()
                .rememberMe().alwaysRemember(true).tokenValiditySeconds(60 * 60 * 24 * 30).key("tocken-id")
                .tokenRepository(persistentRememberMeToken())
                .userDetailsService(userDetailsService)
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/index").failureUrl("/login?error=true")
                .and()
                .logout().logoutSuccessUrl("/index");
    }
}
