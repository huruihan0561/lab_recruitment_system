package com.lab.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lab.entity.Admin;
import com.lab.entity.Student;
import com.lab.mapper.AdminMapper;
import com.lab.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final StudentMapper studentMapper;
    private final AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 先查学生
        Student student = studentMapper.selectOne(new QueryWrapper<Student>().eq("student_id", username));
        if (student != null) {
            return new org.springframework.security.core.userdetails.User(
                    student.getStudentId(),
                    student.getPassword(),
                    Collections.emptyList()
            );
        }

        // 再查管理员
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));
        if (admin != null) {
            return new org.springframework.security.core.userdetails.User(
                    admin.getName(),
                    admin.getPassword(),
                    Collections.emptyList()
            );
        }

        throw new UsernameNotFoundException("用户不存在: " + username);
    }
}