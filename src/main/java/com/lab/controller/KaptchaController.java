package com.lab.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Map;

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
    public Map<String, String> kaptchaImage(HttpSession session) throws IOException {
        String text = kaptcha.createText();
        String key = session.getId();
        redisTemplate.opsForValue().set("kaptcha:" + key, text, Duration.ofMinutes(5));

        // 返回验证码图片和 key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(kaptcha.createImage(text), "jpg", os);
        String base64 = Base64.getEncoder().encodeToString(os.toByteArray());

        return Map.of(
                "key", key,
                "image", "data:image/jpeg;base64," + base64
        );
    }
}
