package com.lab.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI labOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("实验室纳新系统API")
                        .description("实验室纳新系统的接口文档，包含用户认证、信息查询等功能")
                        .version("2.0")
                        .contact(new Contact()
                                .name("TC实验室纳新QQ群：xxx")));
    }
}
    