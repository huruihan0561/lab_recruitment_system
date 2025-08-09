package com.lab.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class InterviewStudentDTO {
    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "学生学号不能为空")
    private String studentId;

    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "意向方向不能为空")
    private String direction;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @NotBlank(message = "座右铭不能为空")
    private String motto;

    @NotNull(message = "面试时间不能为空")
    private Date applyTime;
}
