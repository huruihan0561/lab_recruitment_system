package com.lab.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "学生登录参数DTO")
public class LoginDTO {
    @Schema(description = "学号")
    @NotNull(message = "学号不能为空")
    private String studentId;
    @Schema(description = "密码")
    @NotNull(message = "密码不能为空")
    private String password;

}
