package com.lab.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lab.dto.AdminLoginDTO;
import com.lab.dto.UpdateInterviewResultDTO;
import com.lab.entity.InterviewResult;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;

public interface AdminService {
    String login(AdminLoginDTO dto);
    IPage<Student> listStudents(int current, int size);
    IPage<InterviewStudent> listInterviewStudents(int current, int size);
    IPage<InterviewResult> listInterviewResults(int current, int size);
    void addInterviewStudent(InterviewStudent stu);
    void deleteInterviewStudent(String id);
    IPage<Student> searchStudentsByName(int current, int size, String name);
    void updateInterviewResult(UpdateInterviewResultDTO dto);
    void updateInterviewStudent(InterviewStudent stu);
}
