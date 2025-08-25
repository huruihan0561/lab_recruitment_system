package com.lab.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "管理员注册参数DTO")
public class AdminRegisterDTO {

    @NotBlank(message = "管理员编号不能为空")
    @Pattern(regexp = "^admin\\d+$", message = "管理员编号格式错误")
    @Schema(description = "管理员编号（如admin001）")
    private String adminId;

    @NotBlank(message = "管理员姓名不能为空")
    @Schema(description = "姓名")
    private String name;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码长度不能小于6位")
    @Schema(description = "密码")
    private String password;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误")
    @Schema(description = "电话号码")
    private String phone;
}
