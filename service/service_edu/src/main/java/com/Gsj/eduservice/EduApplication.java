package com.Gsj.eduservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//启动类
@SpringBootApplication
@ComponentScan(basePackages = {"com.Gsj"})//开启组件扫描，扫描com.Gsj包下的所有配置组件
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class);
    }
}
