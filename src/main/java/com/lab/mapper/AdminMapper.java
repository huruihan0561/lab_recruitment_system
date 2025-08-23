package com.lab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lab.entity.Admin;
import com.lab.entity.Student;
import com.lab.vo.AdminStudentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("SELECT * FROM admin WHERE name = #{name}")
    Admin selectByUsername(@Param("username") String username);

    // 模糊搜索学生
    IPage<Student> selectStudentsByName(Page<Student> page, @Param("name") String name);

    //面试结果多表联查
    @Select("SELECT r.id, r.student_id, s.name, r.status " +
            "FROM interview_result r JOIN student s ON r.student_id = s.student_id")
    IPage<AdminStudentVO> selectInterviewResults(Page<AdminStudentVO> page);

    @Update("UPDATE student SET password = #{newPwd} WHERE student_id = #{id}")
    int updatePassword(@Param("id") String id, @Param("newPwd") String newPwd);
}
