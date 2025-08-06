package com.lab.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {

        @NotBlank(message = "姓名不能为空")
        private String name;
        @NotBlank(message = "专业不能为空")
        private String major;
        @NotBlank(message = "学号不能为空")
        private String studentId;
        @NotBlank(message = "手机号不能为空")
        @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误")
        private String phone;
        @NotBlank(message = "邮箱不能为空")
        @Email(message = "邮箱格式错误")
        private String email;
        @NotBlank(message = "密码不能为空")
        @Size(min = 6, message = "密码长度不能小于6位")
        private String password;
    }
