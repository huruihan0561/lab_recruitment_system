package com.lab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lab.entity.Student;
import com.lab.vo.InterviewResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("SELECT r.id, r.student_id, s.name, r.status " +
            "FROM interview_result r JOIN student s ON r.student_id = s.student_id " +
            "WHERE r.student_id = #{studentId}")
    List<InterviewResultVO> selectInterviewResults(@Param("studentId") String studentId);

    @Update("UPDATE student SET password = #{newPwd} WHERE student_id = #{id}")
    int updatePassword(@Param("id") String id, @Param("newPwd") String newPwd);
}