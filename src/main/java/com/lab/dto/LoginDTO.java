package com.lab.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "学生登录参数DTO")
public class LoginDTO {
    @Schema(description = "学号")
    @NotBlank(message = "学号不能为空")
    private String studentId;
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    @Schema(description = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String kaptcha;

}
