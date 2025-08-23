package com.lab.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateInterviewResultDTO {
    @NotNull(message = "学生ID不能为空")
    private String studentId;

    @NotBlank(message = "面试状态不能为空")
    private String status; // 已通过、未通过、待面试

    private String remark;
}