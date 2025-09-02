package com.lab.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        // 1. 配置 CORS 规则
        CorsConfiguration config = new CorsConfiguration();
      //  config.addAllowedOrigin("http://your-frontend.com"); // 允许的前端域名（生产环境用）
         config.addAllowedOrigin("*"); // 允许所有域名（开发环境可用，生产环境禁用）

        config.addAllowedMethod("*"); // 允许所有请求方法（GET/POST/PUT 等）
        config.addAllowedHeader("*"); // 允许所有请求头（如 Content-Type、Authorization）
        config.setAllowCredentials(true); // 允许携带 Cookie（需前端请求也开启 withCredentials）

        // 2. 配置哪些接口生效（/** 表示所有接口）
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // 3. 返回过滤器
        return new CorsFilter(source);
    }

}
