package com.lab.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lab.entity.Admin;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;

import java.util.List;

public interface AdminService {
    String login(String username, String password);
    List<Student> listStudents(long current, long size);
    List<InterviewStudent> listInterviewStudents();
    void addInterviewStudent(InterviewStudent stu);
    void updateInterviewStudent(InterviewStudent stu);
    void deleteInterviewStudent(Integer id);
}
