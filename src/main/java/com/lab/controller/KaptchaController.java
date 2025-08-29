package com.lab.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.time.Duration;

@RestController
@RequestMapping("/kaptcha")
@Tag(name = "验证码接口",description = "生成与校验验证码")
@RequiredArgsConstructor
public class KaptchaController {
    @Autowired
    private DefaultKaptcha kaptcha;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/image")
    public void kaptchaImage(HttpSession session, HttpServletResponse response) throws IOException {
        String text = kaptcha.createText();
        redisTemplate.opsForValue().set("kaptcha:" + session.getId(), text, Duration.ofMinutes(5));
        response.setContentType("image/jpeg");
        ImageIO.write(kaptcha.createImage(text), "jpg", response.getOutputStream());
    }
}
