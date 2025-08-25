package com.lab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lab.entity.InterviewStudent;
import com.lab.vo.InterviewResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InterviewStudentMapper extends BaseMapper<InterviewStudent> {
    @Select("SELECT * FROM interview_student WHERE student_id = #{studentId}")
    InterviewStudent selectByStudentId(@Param("studentId") String studentId);
}
