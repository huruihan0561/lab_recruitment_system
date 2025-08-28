package com.lab.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "学生注册参数DTO")
public class RegisterDTO {

        @Schema(description = "学生姓名")
        @NotNull(message = "姓名不能为空！")
        private String name;

        @Schema(description = "年级")
        @NotNull(message = "年级不能为空！")
        private String grade;

        @Schema(description = "学生专业")
        @NotNull(message = "专业不能为空！")
        private String major;

        @Schema(description = "学生学号")
        @NotNull(message = "学号不能为空！")
        private String studentId;

        @Schema(description = "电话")
        @NotNull(message = "手机号不能为空！")
        @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误！")
        private String phone;

        @Schema(description = "邮箱")
        @NotNull(message = "邮箱不能为空！")
        @Email(message = "邮箱格式错误！")
        private String email;

        @Schema(description = "密码")
        @NotNull(message = "密码不能为空！")
        @Size(min = 6, message = "密码长度不能小于6位！")
        private String password;
    }
