package com.lab.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lab.entity.Admin;
import com.lab.entity.Student;
import com.lab.mapper.AdminMapper;
import com.lab.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentMapper studentMapper;
    private final AdminMapper adminMapper;


    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // 首先尝试查找学生
        Student student = studentMapper.selectOne(
                new QueryWrapper<Student>().eq("student_id", id));
        if (student != null) {
            return new User(
                    student.getStudentId(),
                    student.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT"))
            );
        }

        // 然后尝试查找管理员
        Admin admin = adminMapper.selectOne(
                new QueryWrapper<Admin>().eq("admin_id", id));
        if (admin != null) {
            return new User(
                    admin.getAdminId(),
                    admin.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        }

        throw new UsernameNotFoundException("用户不存在");
    }

}
