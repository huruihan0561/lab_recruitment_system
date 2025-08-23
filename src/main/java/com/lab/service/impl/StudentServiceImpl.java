package com.lab.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lab.dto.InterviewStudentDTO;
import com.lab.dto.LoginDTO;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;
import com.lab.dto.RegisterDTO;
import com.lab.mapper.InterviewStudentMapper;
import com.lab.mapper.StudentMapper;
import com.lab.service.StudentService;
import com.lab.util.JwtUtil;
import com.lab.util.PasswordUtils;
import com.lab.util.ValidatorUtil;
import com.lab.vo.DirectionVO;
import com.lab.vo.InterviewResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Arrays;
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
        InterviewStudent stu = new InterviewStudent();
        stu.setName(dto.getName());
        stu.setStudentId(dto.getStudentId());
        stu.setDirection(dto.getDirection());
        stu.setMotto(dto.getMotto());
        stu.setInterviewTime(dto.getInterviewTime());
        interviewStudentMapper.insert(stu);
    }

    @Override
    public List<DirectionVO> getDirections() {
        return Arrays.asList(
                new DirectionVO("嵌入式开发", "基础知识： 学习嵌入式系统的基本原理、硬件结构和编程语言（如C、C++）。\n学习硬件： 掌握单片机、传感器、嵌入式系统的设计和开发。\nRTOS学习： 熟悉实时操作系统（RTOS）的使用和应用。\n项目实践： 参与嵌入式项目，如智能家居、物联网设备等，实践应用技能。"),
                new DirectionVO("后台开发", "编程语言： 学习后台开发常用的编程语言，如Java、Python、Node.js等。\n数据库： 掌握数据库设计和管理，如MySQL、MongoDB等。\n框架技术： 学习后台开发框架，如Spring、Django等。\n微服务架构： 了解微服务架构和RESTful API设计。\n安全和性能优化： 学习如何保障系统安全性和性能优化。"),
                new DirectionVO("前端开发", "HTML/CSS/JavaScript： 掌握前端基础技术，构建网页布局和交互效果。\n框架和库： 学习流行的前端框架和库，如React、Vue.js等。\n响应式设计： 了解响应式网页设计和移动端适配。\n版本控制： 掌握Git等版本控制工具的使用。\n跨平台开发： 学习跨平台开发技术，如React Native、Flutter等。"),
                new DirectionVO("安卓开发", "Java/Kotlin： 掌握Java或Kotlin编程语言。\nAndroid SDK： 学习Android开发工具和框架。\n布局和界面设计： 了解Android布局和用户界面设计。\n数据存储： 学习Android数据存储技术，如SQLite、Room等。\n发布和优化： 学习应用发布流程和性能优化技巧。")
        );
    }

    @Override
    public List<InterviewResultVO> getInterviewResult(String studentId) {
        return studentMapper.selectInterviewResults(studentId);
    }
}
