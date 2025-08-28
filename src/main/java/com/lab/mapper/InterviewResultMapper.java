package com.lab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lab.entity.InterviewResult;
import com.lab.vo.InterviewResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface InterviewResultMapper extends BaseMapper<InterviewResult> {
    @Select("SELECT r.*, s.name " +
            "FROM interview_result r JOIN student s ON r.student_id = s.student_id")
    List<InterviewResult> selectInterviewResults();

}