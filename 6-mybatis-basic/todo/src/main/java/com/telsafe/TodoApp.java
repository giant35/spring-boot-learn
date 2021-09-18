package com.telsafe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.telsafe.mapper")
public class TodoApp {
    public static void main(String[] args) {
        SpringApplication.run(TodoApp.class, args);
    }
}