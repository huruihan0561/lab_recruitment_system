package com.lab.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // 针对验证码接口的跨域配置
        registry.addMapping("/kaptcha/image")  // 专门指定验证码接口
                .allowedOriginPatterns("*")  // 开发环境可先用*，生产环境替换为前端实际域名
                .allowedMethods("GET", "OPTIONS")  // 验证码接口通常用GET
                .allowedHeaders("Content-Type", "Authorization")  // 按需求添加请求头
                .allowCredentials(true)
                .maxAge(3600);
        // 配置跨域规则
        registry.addMapping("/**")  // 对所有接口生效
                .allowedOriginPatterns("*")  // 允许的源（生产环境替换为具体域名）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的请求方法
                .allowedHeaders("*")  // 允许的请求头
                .allowCredentials(true)  // 允许携带Cookie
                .maxAge(3600);  // 预检请求的有效期（秒）
    }
}
