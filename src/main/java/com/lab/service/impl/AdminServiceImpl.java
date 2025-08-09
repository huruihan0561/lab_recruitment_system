package com.lab.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lab.dto.AdminLoginDTO;
import com.lab.dto.AdminRegisterDTO;
import com.lab.entity.Admin;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;
import com.lab.mapper.AdminMapper;
import com.lab.mapper.InterviewResultMapper;
import com.lab.mapper.InterviewStudentMapper;
import com.lab.mapper.StudentMapper;
import com.lab.service.AdminService;
import com.lab.util.JwtUtil;
import com.lab.util.PasswordUtils;
import com.lab.util.ValidatorUtil;
import com.lab.vo.InterviewResultVO;
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
    @Autowired
    private InterviewResultMapper interviewResultMapper;


    @Override
    public void register(AdminRegisterDTO dto) {
        ValidatorUtil.validatePhone(dto.getPhone());
        if (dto.getPassword().length() < 6) throw new IllegalArgumentException("密码不足6位");
        if (adminMapper.selectCount(new QueryWrapper<Admin>().eq("admin_id", dto.getAdminId())) > 0)
            throw new IllegalArgumentException("学号已存在");

        Admin admin = new Admin();
        admin.setAdminId(dto.getAdminId());
        admin.setUsername(dto.getUsername());
        admin.setPassword(PasswordUtils.encode(dto.getPassword()));
        admin.setPhone(dto.getPhone());
        adminMapper.insert(admin);
    }

    @Override
    public String login(AdminLoginDTO dto) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("admin_id", dto.getAdminId()));
        if (admin == null || !PasswordUtils.matches(dto.getPassword(), admin.getPassword()))
            throw new IllegalArgumentException("学号或密码错误");
        return jwtUtil.generateToken(admin.getAdminId());
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

    @Override
    public List<InterviewResultVO> listInterviewResults() {
        return interviewResultMapper.selectInterviewResults();
    }
}
