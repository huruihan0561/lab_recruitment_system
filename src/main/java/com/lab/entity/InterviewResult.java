package com.lab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("interview_result")
@Schema(description = "面试结果实体类")
public class InterviewResult {
    @TableId(type = IdType.AUTO)
    @Schema(description = "结果ID")
    private Integer id;
    
    @Schema(description = "学生学号")
    private String studentId;
    
    @Schema(description = "学生姓名")
    private String studentName;
    
    @Schema(description = "面试结果状态：通过/未通过/待面试")
    private String status;
}
