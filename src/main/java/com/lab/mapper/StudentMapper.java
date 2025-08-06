package com.lab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lab.entity.InterviewResult;
import com.lab.entity.Student;
import com.lab.vo.InterviewResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    @Select("SELECT r.id, r.student_id, s.name, r.status " +
            "FROM interview_result r " +
            "JOIN student s ON r.student_id = s.student_id " +
            "WHERE r.student_id = #{studentId}")
    List<InterviewResultVO> selectInterviewResults(@Param("studentId") String studentId);

    IPage<Student> findByNameLike(Page<Student> page, @Param("name") String name);
}