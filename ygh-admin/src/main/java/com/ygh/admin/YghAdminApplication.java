package com.ygh.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ygh"})
@MapperScan("com.ygh.admin.mapper")
public class YghAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YghAdminApplication.class, args);
    }

}
