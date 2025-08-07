package com.lab.service;

import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;
import com.lab.vo.InterviewResultVO;

import java.util.List;

public interface AdminService {
    String login(String username, String password);
    List<Student> listStudents(long current, long size);
    List<InterviewStudent> listInterviewStudents();
    void addInterviewStudent(InterviewStudent stu);
    void updateInterviewStudent(InterviewStudent stu);
    void deleteInterviewStudent(Integer id);
    List<InterviewResultVO> listInterviewResults();
}
