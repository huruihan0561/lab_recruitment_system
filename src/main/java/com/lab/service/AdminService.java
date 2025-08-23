package com.lab.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lab.dto.AdminLoginDTO;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;
import com.lab.vo.AdminStudentVO;

public interface AdminService {
    String login(AdminLoginDTO dto);
    IPage<Student> listStudents(int current, int size);
    IPage<InterviewStudent> listInterviewStudents(int current, int size);
    IPage<AdminStudentVO> listInterviewResults(int current, int size);
    void addInterviewStudent(InterviewStudent stu);
    void updateInterviewStudent(InterviewStudent stu);
    void deleteInterviewStudent(Integer id);
    IPage<Student> searchStudentsByName(int current, int size, String name);
}
