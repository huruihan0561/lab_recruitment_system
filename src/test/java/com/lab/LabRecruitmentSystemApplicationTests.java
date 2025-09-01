package com.lab;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
@MapperScan("com.lab.mapper")
class LabRecruitmentSystemApplicationTests {

    @Test
   void contextLoads() {

    }

}
