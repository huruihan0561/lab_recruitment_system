package com.lab.util;

import com.lab.vo.ResultVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class KaptchaValidator {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public ResultVO<Void> validate(String kaptcha, String key) {
        String cacheKey = "kaptcha:" + key;
        String realKaptcha = redisTemplate.opsForValue().get(cacheKey);
        if (realKaptcha == null) {
            throw new IllegalArgumentException("验证码已过期，请重新获取");
        }
        if (!realKaptcha.equalsIgnoreCase(kaptcha)) {
            throw new IllegalArgumentException("验证码错误，请重新输入");
        }
        redisTemplate.delete(cacheKey);
        return null;
    }
}
