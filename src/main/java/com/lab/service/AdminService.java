package com.lab.service;

import com.github.pagehelper.PageInfo;
import com.lab.dto.AdminLoginDTO;
import com.lab.dto.UpdateInterviewResultDTO;
import com.lab.entity.InterviewResult;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;

public interface AdminService {
    String login(AdminLoginDTO dto);
    PageInfo<Student> listStudents(int current, int size);
    PageInfo<InterviewStudent> listInterviewStudents(int current, int size);
    PageInfo<InterviewResult> listInterviewResults(int current, int size);
    PageInfo<Student> searchStudentsByName(int current, int size, String name);
    void addInterviewStudent(InterviewStudent stu);
    void deleteInterviewStudent(String id);
    void updateInterviewResult(UpdateInterviewResultDTO dto);
    void updateInterviewStudent(InterviewStudent stu);
}
