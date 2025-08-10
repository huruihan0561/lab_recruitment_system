package com.lab.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.lab.vo.ResultVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.io.IOException;

@RestController
@RequestMapping("/captcha")
@Tag(name = "验证码接口",description = "生成与校验验证码")
@RequiredArgsConstructor
public class CaptchaController {

    private final DefaultKaptcha kaptcha;
    private final StringRedisTemplate redisTemplate;

    // 生成验证码图片
    @GetMapping("/image")
    public void captchaImage(HttpSession session, HttpServletResponse response) throws IOException {
        String text = kaptcha.createText();
        session.setAttribute("captcha", text);          // 直接放 session
        response.setContentType("image/jpeg");
        ImageIO.write(kaptcha.createImage(text), "jpg", response.getOutputStream());
    }

    // 校验验证码
    @PostMapping("/verify")
    public ResultVO<Void> verify(@RequestParam String captcha, HttpSession session) {
        String cache = (String) session.getAttribute("captcha");
        if (!captcha.equalsIgnoreCase(cache)) {
            return ResultVO.fail("验证码错误");
        }
        session.removeAttribute("captcha");   // 一次性
        return ResultVO.success();
    }
}
