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
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("SELECT * FROM admin WHERE name = #{name}")
    Admin selectByName(@Param("name") String name);

    @Select("SELECT * FROM admin WHERE admin_id = #{adminId}")
    Admin selectByAdminId(@Param("adminId") String adminId);

    // 模糊搜索学生
    @Select("SELECT * FROM student WHERE name LIKE CONCAT('%', #{name}, '%')")
    IPage<Student> selectStudentsByName(Page<Student> page, @Param("name") String name);

    @Select("SELECT r.* " +
            "FROM interview_result r " +
            "LEFT JOIN student s ON r.student_id = s.student_id")
    IPage<InterviewResult> selectInterviewResults(Page<InterviewResult> page);
}
