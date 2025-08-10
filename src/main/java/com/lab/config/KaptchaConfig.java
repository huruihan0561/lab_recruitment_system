package com.lab.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

//kaptcha验证码配置
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha kaptchaProducer() {
        //创建验证码生成器对象
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        //设置参数
        Properties prop = new Properties();
        //关闭验证码图片的边框
        prop.put("kaptcha.border", "no");
        //验证码字符为黑色
        prop.put("kaptcha.textproducer.font.color", "black");
        //验证码字符间隔5个像素
        prop.put("kaptcha.textproducer.char.space", "5");
        //验证码字符数量为4
        prop.put("kaptcha.textproducer.char.length", "4");
        //验证码图片尺寸：宽120 高40
        prop.put("kaptcha.image.width", "120");
        prop.put("kaptcha.image.height", "40");
        kaptcha.setConfig(new Config(prop));
        return kaptcha;
    }
}
