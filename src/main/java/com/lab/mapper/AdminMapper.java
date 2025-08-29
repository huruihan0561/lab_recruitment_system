package com.lab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lab.entity.Admin;
import com.lab.entity.InterviewResult;
import com.lab.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    // 模糊搜索学生
    @Select("SELECT * FROM student WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<Student> selectStudentsByName(@Param("name") String name);

    @Select("SELECT r.* FROM interview_result r LEFT JOIN student s ON r.student_id = s.student_id")
    List<InterviewResult> selectInterviewResults();
}
