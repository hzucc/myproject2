package com.example.myproject2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@MapperScan("com.example.myproject2.dao")
public class Myproject2Application {

    public static void main(String[] args) {
        SpringApplication.run(Myproject2Application.class, args);
    }

}
