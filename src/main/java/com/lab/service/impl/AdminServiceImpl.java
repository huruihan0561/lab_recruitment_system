package com.lab.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.dto.AdminLoginDTO;
import com.lab.dto.UpdateInterviewResultDTO;
import com.lab.entity.Admin;
import com.lab.entity.InterviewResult;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;
import com.lab.mapper.AdminMapper;
import com.lab.mapper.InterviewResultMapper;
import com.lab.mapper.InterviewStudentMapper;
import com.lab.mapper.StudentMapper;
import com.lab.service.AdminService;
import com.lab.util.JwtUtil;
import com.lab.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
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
    public String login(AdminLoginDTO dto) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("admin_id", dto.getAdminId()));
        if (admin == null || !PasswordUtils.matches(dto.getPassword(), admin.getPassword()))
            throw new IllegalArgumentException("学号或密码错误");
        return jwtUtil.generateToken(admin.getAdminId());
    }


    @Override
    public PageInfo<Student> listStudents(int current, int size) {
        PageHelper.startPage(current, size);
        List<Student> students = studentMapper.selectList(null);
        return new PageInfo<>(students);
    }


    @Override
    public PageInfo<InterviewStudent> listInterviewStudents(int current, int size) {
        PageHelper.startPage(current, size);
        List<InterviewStudent> list = interviewStudentMapper.selectList(null);
        return new PageInfo<>(list);
    }


    @Override
    public PageInfo<InterviewResult> listInterviewResults(int current, int size) {
        PageHelper.startPage(current, size);
        List<InterviewResult> results = adminMapper.selectInterviewResults();
        return new PageInfo<>(results);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addInterviewStudent(InterviewStudent stu) {
        interviewStudentMapper.insert(stu);
        InterviewResult result = new InterviewResult();
        result.setStudentId(stu.getStudentId());
        result.setStudentName(stu.getName());
        result.setDirection(stu.getDirection());
        result.setInterviewMethod(stu.getInterviewMethod());
        result.setStatus("待面试");
        result.setInterviewTime(stu.getInterviewTime());
        interviewResultMapper.insert(result);
    }

    @Override
    public void updateInterviewStudent(InterviewStudent updatedStu) {
        InterviewStudent existing = interviewStudentMapper.selectByStudentId(updatedStu.getStudentId());
        if (existing == null) {
            throw new IllegalArgumentException("该学生未报名");
        }
        if (!existing.getStudentId().equals(updatedStu.getStudentId())) {
            throw new IllegalArgumentException("学号不允许修改");
        }
        UpdateWrapper<InterviewStudent> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("student_id", updatedStu.getStudentId());
        if (updatedStu.getName() != null) {
            updateWrapper.set("name", updatedStu.getName());
        }
        if (updatedStu.getDirection() != null) {
            updateWrapper.set("direction", updatedStu.getDirection());
        }
        if (updatedStu.getInterviewTime() != null) {
            updateWrapper.set("interview_time", updatedStu.getInterviewTime());
        }
        interviewStudentMapper.update(null, updateWrapper);
        InterviewResult result = interviewResultMapper.selectOne(
                new QueryWrapper<InterviewResult>().eq("student_id", updatedStu.getStudentId())
        );
        if (result != null) {
            if (updatedStu.getName() != null) {
                result.setStudentName(updatedStu.getName());
            }
            if (updatedStu.getDirection() != null) {
                result.setDirection(updatedStu.getDirection());
            }
            if (updatedStu.getInterviewTime() != null) {
                result.setInterviewTime(updatedStu.getInterviewTime());
            }
            interviewResultMapper.updateById(result);
        }
    }

    @Override
    public void deleteInterviewStudent(String studentId) {
        // 1. 删除报名学生
        interviewStudentMapper.delete(new QueryWrapper<InterviewStudent>().eq("student_id", studentId));
        // 2. 删除面试结果
        interviewResultMapper.delete(new QueryWrapper<InterviewResult>().eq("student_id", studentId));
    }


    @Override
    public PageInfo<Student> searchStudentsByName(int current, int size, String name) {
        PageHelper.startPage(current, size);
        List<Student> students = adminMapper.selectStudentsByName(name);
        return new PageInfo<>(students);
    }

    @Override
    public void updateInterviewResult(UpdateInterviewResultDTO dto) {
        InterviewResult result = interviewResultMapper.selectOne(
                new QueryWrapper<InterviewResult>().eq("student_id", dto.getStudentId())
        );
        if (result == null) {
            throw new IllegalArgumentException("该学生未报名或不存在面试记录");
        }
        List<String> validStatuses = Arrays.asList("未通过", "已通过");
        if (!validStatuses.contains(dto.getStatus())) {
            throw new IllegalArgumentException("无效的面试状态");
        }
        result.setStatus(dto.getStatus());
        result.setRemark(dto.getRemark());
        interviewResultMapper.updateById(result);
    }
}
