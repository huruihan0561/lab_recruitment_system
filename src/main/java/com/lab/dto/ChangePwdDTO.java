package com.lab.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "修改密码请求参数DTO")
public class ChangePwdDTO {

    @NotBlank
    @Schema(description = "用户学号")
    private String id;
    @NotBlank
    @Schema(description = "原始密码")
    private String oldPassword;
    @NotBlank
    @Size(min = 6)
    @Schema(description = "新密码")
    private String newPassword;
}
