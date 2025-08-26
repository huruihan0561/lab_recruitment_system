package com.lab.config;

import com.lab.entity.Admin;
import com.lab.mapper.AdminMapper;
import com.lab.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private AdminMapper adminMapper;


    @Override
    @Transactional
    public void run(String... args) {
        try {
            if (adminMapper.selectCount(null) == 0) {
                Admin root = new Admin();
                root.setAdminId("admin001");
                root.setName("系统管理员");
                root.setPassword(PasswordUtils.encode("admin123"));
                root.setPhone("17000000000");
                adminMapper.insert(root);
                log.info("初始化管理员账号成功：admin001");
            }
        } catch (Exception e) {
            log.error("初始化管理员账号失败", e);
        }
    }
}
