package com.lab.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "管理员登录参数DTO")
public class AdminLoginDTO {
    @NotBlank(message = "学号不能为空")
    private String adminId;

    @NotBlank(message = "密码不能为空")
    private String password;
}
