package com.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LabRecruitmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabRecruitmentSystemApplication.class, args);
    }

}
