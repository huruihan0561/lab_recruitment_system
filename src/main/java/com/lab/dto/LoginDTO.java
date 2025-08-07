package com.lab.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "登录参数DTO")
public class LoginDTO {
    @NotNull(message = "学号/用户名不能为空")
    private String studentId;
    @NotNull(message = "密码不能为空")
    private String password;

}
