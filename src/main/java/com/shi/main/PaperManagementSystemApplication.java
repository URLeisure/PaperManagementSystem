package com.shi.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shi.main.mapper")
public class PaperManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperManagementSystemApplication.class, args);
    }

}
