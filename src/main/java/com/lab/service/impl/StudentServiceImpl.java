package com.lab.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lab.dto.InterviewStudentDTO;
import com.lab.dto.LoginDTO;
import com.lab.entity.InterviewResult;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;
import com.lab.dto.RegisterDTO;
import com.lab.mapper.InterviewResultMapper;
import com.lab.mapper.InterviewStudentMapper;
import com.lab.mapper.StudentMapper;
import com.lab.service.StudentService;
import com.lab.util.JwtUtil;
import com.lab.util.PasswordUtils;
import com.lab.util.ValidatorUtil;
import com.lab.vo.InterviewResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private InterviewStudentMapper interviewStudentMapper;
    @Autowired
    private InterviewResultMapper interviewResultMapper;

    @Override
    public void register(RegisterDTO dto) {
        ValidatorUtil.validatePhone(dto.getPhone());
        if (dto.getPassword().length() < 6) throw new IllegalArgumentException("密码不足6位");
        if (studentMapper.selectCount(new QueryWrapper<Student>().eq("student_id", dto.getStudentId())) > 0)
            throw new IllegalArgumentException("学号已存在");

        Student stu = new Student();
        stu.setName(dto.getName());
        stu.setStudentId(dto.getStudentId());
        stu.setMajor(dto.getMajor());
        stu.setPhone(dto.getPhone());
        stu.setEmail(dto.getEmail());
        stu.setPassword(PasswordUtils.encode(dto.getPassword()));
        studentMapper.insert(stu);
    }

    @Override
    public String login(LoginDTO dto) {
        Student stu = studentMapper.selectOne(new QueryWrapper<Student>()
                .eq("student_id", dto.getStudentId()));
        if (stu == null || !PasswordUtils.matches(dto.getPassword(), stu.getPassword()))
            throw new IllegalArgumentException("学号或密码错误");
        return jwtUtil.generateToken(stu.getStudentId());
    }

    @Override
    public void apply(InterviewStudentDTO dto) {
        if (interviewStudentMapper.exists(
                new QueryWrapper<InterviewStudent>().eq("student_id", dto.getStudentId()))) {
            throw new IllegalArgumentException("已报名，请勿重复提交");
        }
        Student student = studentMapper.selectOne(
                new QueryWrapper<Student>().eq("student_id", dto.getStudentId()));
        if (student == null) {
            throw new IllegalArgumentException("学号不存在");
        }

        InterviewStudent stu = new InterviewStudent();
        stu.setStudentId(dto.getStudentId());
        stu.setName(dto.getName());
        stu.setEmail(student.getEmail());
        stu.setPhone(student.getPhone());
        stu.setDirection(dto.getDirection());
        stu.setMotto(dto.getMotto());
        stu.setInterviewTime(dto.getInterviewTime());
        interviewStudentMapper.insert(stu);

        InterviewResult result = new InterviewResult();
        result.setStudentId(dto.getStudentId());
        result.setStudentName(dto.getName());
        result.setDirection(dto.getDirection());
        result.setStatus("待面试");
        result.setInterviewTime(dto.getInterviewTime());
        interviewResultMapper.insert(result);
    }

    @Override
    public List<InterviewResultVO> getInterviewResult(String studentId) {
        //验证学生是否存在
        Student student = studentMapper.selectOne(
                new QueryWrapper<Student>().eq("student_id", studentId));
        if (student == null) {
            throw new IllegalArgumentException("学生不存在");
        }

        List<InterviewResultVO> results = studentMapper.selectInterviewResults(studentId);
        if (results.isEmpty()) {
            throw new IllegalArgumentException("暂无面试结果");
        }

        return results;
    }
}
