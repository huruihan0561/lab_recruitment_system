package com.lab.config;

import com.lab.entity.Admin;
import com.lab.mapper.AdminMapper;
import com.lab.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void run(String... args) throws Exception {
        if (adminMapper.selectCount(null) == 0) {
            Admin root = new Admin();
            root.setAdminId("admin001");
            root.setName("系统管理员");
            root.setPassword(PasswordUtils.encode("admin123"));
            root.setPhone("17000000000");
            adminMapper.insert(root);
        }

    }
}
