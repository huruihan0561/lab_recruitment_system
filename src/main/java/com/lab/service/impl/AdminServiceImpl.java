package com.lab.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lab.entity.Admin;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;
import com.lab.mapper.AdminMapper;
import com.lab.mapper.InterviewStudentMapper;
import com.lab.mapper.StudentMapper;
import com.lab.service.AdminService;
import com.lab.util.JwtUtil;
import com.lab.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

        @Autowired
        private AdminMapper adminMapper;
        @Autowired
        private StudentMapper studentMapper;
        @Autowired
        private InterviewStudentMapper interviewStudentMapper;
        @Autowired
        private JwtUtil jwtUtil;

        @Override
        public String login(String username, String password) {
            Admin admin = adminMapper.selectByUsername(username);
            if (admin == null || !PasswordUtils.matches(password, admin.getPassword()))
                throw new IllegalArgumentException("账号或密码错误");
            return jwtUtil.generateToken("admin_" + admin.getId());
        }

        @Override
        public List<Student> listStudents(long current, long size) {
            return studentMapper.selectPage(new Page<>(current, size), null).getRecords();
        }

        @Override
        public List<InterviewStudent> listInterviewStudents() {
            return interviewStudentMapper.selectList(null);
        }

        @Override
        public void addInterviewStudent(InterviewStudent stu) {
            interviewStudentMapper.insert(stu);
        }

        @Override
        public void updateInterviewStudent(InterviewStudent stu) {
            interviewStudentMapper.updateById(stu);
        }

        @Override
        public void deleteInterviewStudent(Integer id) {
            interviewStudentMapper.deleteById(id);
        }


}
