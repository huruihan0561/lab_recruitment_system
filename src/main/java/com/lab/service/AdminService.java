package com.lab.service;

import com.lab.dto.AdminLoginDTO;
import com.lab.dto.AdminRegisterDTO;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;
import com.lab.vo.InterviewResultVO;

import java.util.List;

public interface AdminService {
    void register(AdminRegisterDTO dto);
    String login(AdminLoginDTO dto);
    List<Student> listStudents(long current, long size);
    List<InterviewStudent> listInterviewStudents();
    void addInterviewStudent(InterviewStudent stu);
    void updateInterviewStudent(InterviewStudent stu);
    void deleteInterviewStudent(Integer id);
    List<InterviewResultVO> listInterviewResults();
}
