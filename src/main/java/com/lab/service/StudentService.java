package com.lab.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lab.dto.InterviewStudentDTO;
import com.lab.dto.LoginDTO;
import com.lab.entity.Student;
import com.lab.dto.RegisterDTO;
import com.lab.vo.DirectionVO;
import com.lab.vo.InterviewResultVO;

import java.util.List;

public interface StudentService {
    void register(RegisterDTO dto);
    String login(LoginDTO dto);
    void apply(InterviewStudentDTO dto);
    List<InterviewResultVO> getInterviewResult(String studentId);
}
