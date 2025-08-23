package com.lab.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "学生报名请求参数DTO")
public class InterviewStudentDTO {
    @Schema(description = "学生姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @Schema(description = "学生学号")
    @NotBlank(message = "学生学号不能为空")
    private String studentId;

    @Schema(description = "意向方向")
    @NotBlank(message = "意向方向不能为空")
    private String direction;

    @Schema(description = "座右铭")
    @NotBlank(message = "座右铭不能为空")
    private String motto;

    @Schema(description ="面试时间")
    @NotNull(message = "面试时间不能为空")
    private Date interviewTime;
}
