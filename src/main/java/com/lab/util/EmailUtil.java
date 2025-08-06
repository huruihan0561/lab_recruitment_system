package com.lab.util;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;



@Component
public class EmailUtil {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单文本邮件
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    /**
     * 发送验证码邮件
     */
    public void sendVerificationCode(String to, String code) {
        String subject = "实验室纳新系统 - 验证码";
        String content = String.format("您的验证码是：%s，有效期5分钟。请勿向他人泄露。", code);
        sendSimpleMail(to, subject, content);
    }
}
    