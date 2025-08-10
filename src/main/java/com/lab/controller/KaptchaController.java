package com.lab.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.lab.vo.ResultVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

    // 生成验证码图片
    @GetMapping("/image")
    public void kaptchaImage(HttpSession session, HttpServletResponse response) throws IOException {
        String text = kaptcha.createText();
        redisTemplate.opsForValue().set("kaptcha:" + session.getId(), text, Duration.ofMinutes(5));
        response.setContentType("image/jpeg");
        ImageIO.write(kaptcha.createImage(text), "jpg", response.getOutputStream());
    }

    // 校验验证码
    @PostMapping("/verify")
    public ResultVO<Void> verify(@RequestParam String kaptcha, HttpSession session) {
        String cache = redisTemplate.opsForValue().get("kaptcha:" + session.getId());
        if (cache == null || !cache.equalsIgnoreCase(kaptcha)) {
            return ResultVO.fail("验证码错误或已过期");
        }
        // 一次性使用，校验完立即删除
        redisTemplate.delete("captcha:" + session.getId());
        return ResultVO.success();
    }

}
