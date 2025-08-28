package com.lab.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @Schema(description = "面试方式")
    @NotNull(message = "面试方式不能为空")
    private String interviewMethod;

    @Schema(description = "意向方向")
    @NotBlank(message = "意向方向不能为空")
    private String direction;

    @Schema(description = "座右铭")
    @NotBlank(message = "座右铭不能为空")
    private String motto;

    @Schema(description ="面试时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @NotNull(message = "面试时间不能为空")
    private Date interviewTime;
}
