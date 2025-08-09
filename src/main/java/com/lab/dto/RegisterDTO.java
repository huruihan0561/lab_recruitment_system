package com.lab.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Schema(description = "学生注册参数DTO")
public class RegisterDTO {

        @NotNull(message = "姓名不能为空！")
        private String name;

        @NotNull(message = "专业不能为空！")
        private String major;

        @NotNull(message = "学号不能为空！")
        private String studentId;

        @NotNull(message = "手机号不能为空！")
        @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误！")
        private String phone;

        @NotNull(message = "邮箱不能为空！")
        @Email(message = "邮箱格式错误！")
        private String email;

        @NotNull(message = "密码不能为空！")
        @Size(min = 6, message = "密码长度不能小于6位！")
        private String password;
    }
