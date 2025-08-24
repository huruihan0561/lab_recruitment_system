package com.lab.controller;

import com.lab.dto.ChangePwdDTO;
import com.lab.service.PasswordService;
import com.lab.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
@Tag(name = "修改密码接口",description = "修改用户密码")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/change-student")
    @Operation(summary = "学生修改密码")
    public ResultVO<Void> changePassword(@Valid @RequestBody ChangePwdDTO dto) {
        boolean success = passwordService.changeStudentPassword(dto.getId(), dto.getOldPassword(), dto.getNewPassword());
        return success ? ResultVO.success() : ResultVO.fail("修改失败");
    }

    @PostMapping("/change-admin")
    @Operation(summary = "管理员修改密码")
    public ResultVO<Void> changeAdminPassword(@Valid @RequestBody ChangePwdDTO dto) {
        boolean success = passwordService.changeAdminPassword(dto.getId(), dto.getOldPassword(), dto.getNewPassword());
        return success ? ResultVO.success() : ResultVO.fail("修改失败");
    }


}
