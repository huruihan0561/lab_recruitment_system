package com.lab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("interview_student")
@Schema(description = "报名学生实体类")
public class InterviewStudent {
    @TableId(type = IdType.AUTO)
    @Schema(description = "报名ID")
    private Integer id;
    
    @Schema(description = "学生学号")
    private String studentId;
    
    @Schema(description = "学生姓名")
    private String name;
    
    @Schema(description = "报名方向")
    private String direction;
    
    @Schema(description = "报名时间")
    private Date applyTime;
    
    @Schema(description = "面试时间")
    private Integer interviewed;
}
