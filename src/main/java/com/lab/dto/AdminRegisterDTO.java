package com.lab.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "管理员注册参数DTO")
public class AdminRegisterDTO {
    @NotBlank(message = "姓名不能为空")
    private String username;

    @NotBlank(message = "学号不能为空")
    private String adminId;

    @NotNull(message = "密码不能为空！")
    @Size(min = 6, message = "密码长度不能小于6位！")
    private String password;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误！")
    private String phone;
}
