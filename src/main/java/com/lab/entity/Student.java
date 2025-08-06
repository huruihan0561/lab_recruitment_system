package com.lab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("student")
@Schema(description = "学生实体类")
public class Student {
    @TableId(type = IdType.AUTO)
    @Schema(description = "学生ID")
    private Integer id;
    
    @Schema(description = "密码")
    private String password;
    
    @Schema(description = "姓名")
    private String name;
    
    @Schema(description = "专业")
    private String major;
    
    @Schema(description = "学号")
    private String studentId;
    
    @Schema(description = "手机号")
    private String phone;
    
    @Schema(description = "邮箱")
    private String email;
}
