package com.lab.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lab.entity.Admin;
import com.lab.entity.Student;
import com.lab.mapper.AdminMapper;
import com.lab.mapper.StudentMapper;
import com.lab.service.PasswordService;
import com.lab.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean changeStudentPassword(String studentId, String oldPwd, String newPwd) {
        Student stu = studentMapper.selectOne(
                new QueryWrapper<Student>().eq("student_id", studentId));
        if (stu == null || !PasswordUtils.matches(oldPwd, stu.getPassword())) {
            return false;
        }
        return studentMapper.updatePassword(studentId, PasswordUtils.encode(newPwd)) > 0;
    }

    @Override
    public boolean changeAdminPassword(String username, String oldPwd, String newPwd) {
        Admin admin = adminMapper.selectOne(
                new QueryWrapper<Admin>().eq("username", username));
        if (admin == null || !PasswordUtils.matches(oldPwd, admin.getPassword())) {
            return false;
        }
        return adminMapper.updatePassword(username, PasswordUtils.encode(newPwd)) > 0;
    }
}
