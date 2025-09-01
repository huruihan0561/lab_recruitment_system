package com.lab.util;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lab.entity.Student;
import com.lab.mapper.StudentMapper;
import com.lab.vo.ResultVO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.time.Duration;


@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private StudentMapper studentMapper;

    @Value("${spring.mail.username}")
    private String from;

    public ResultVO<Void> sendCode(String email) {
        //验证邮箱是否已注册
        if (studentMapper.exists(Wrappers.<Student>lambdaQuery().eq(Student::getEmail, email))) {
            return ResultVO.fail("邮箱已被注册");
        }

        //防止恶意刷邮件（60s发一次）
        String lockKey = "email_lock:" + email;
        if (redisTemplate.hasKey(lockKey)) {
            return ResultVO.fail("发送过于频繁,请60秒后重试");
        }

        //随机生成6位验证码
        String code = RandomUtil.randomNumbers(6);
        redisTemplate.opsForValue().set("email_code:" + email, code, Duration.ofMinutes(5));
        redisTemplate.opsForValue().set(lockKey, "1", Duration.ofSeconds(60));

        //发邮件
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject("TC实验室纳新系统-验证码");
            helper.setText("您的验证码是：" + code + ",有效期为5分钟。请勿向他人泄露", false);

            mailSender.send(message);
            return ResultVO.success();
        } catch (MessagingException e) {
            // 发送失败时清理Redis数据
            redisTemplate.delete("email_code:" + email);
            redisTemplate.delete(lockKey);
            return ResultVO.fail("邮件发送失败: " + e.getMessage());
        }
    }

        //校验验证码，安全删除
        public boolean validate(String email, String code) {
            String key = "email_code:" + email;
            String cache = redisTemplate.opsForValue().get(key);
            if (cache == null || !cache.equalsIgnoreCase(code)) {
                return false;
            }
            //Lua脚本原子删除
            return Boolean.TRUE.equals(
                    redisTemplate.execute(
                            (RedisCallback<Boolean>) conn -> conn.del(key.getBytes()) > 0
                    )
            );
    }
}
    