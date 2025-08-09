package com.lab.controller;

import com.lab.dto.AdminLoginDTO;
import com.lab.dto.AdminRegisterDTO;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;
import com.lab.service.AdminService;
import com.lab.vo.InterviewResultVO;
import com.lab.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "管理员相关接口", description = "提供管理员管理学生、面试结果等功能")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    @Operation(summary = "管理员注册")
    public ResultVO<Void> register(@Validated @RequestBody AdminRegisterDTO dto) {
        adminService.register(dto);
        return ResultVO.success();
    }


    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public ResultVO<String> login(@Validated @RequestBody AdminLoginDTO dto) {
        return ResultVO.success(adminService.login(dto));
    }

    @GetMapping("/students")
    @Operation(summary = "分页查看注册学生")
    public ResultVO<List<Student>> students(@RequestParam(defaultValue = "1") long current,
                                            @RequestParam(defaultValue = "10") long size) {
        return ResultVO.success(adminService.listStudents(current, size));
    }

    @GetMapping("/interview-students")
    @Operation(summary = "查看所有报名学生")
    public ResultVO<List<InterviewStudent>> interviewStudents() {
        return ResultVO.success(adminService.listInterviewStudents());
    }

    @PostMapping("/interview-student")
    @Operation(summary = "添加报名学生")
    public ResultVO<Void> add(@RequestBody InterviewStudent stu) {
        adminService.addInterviewStudent(stu);
        return ResultVO.success();
    }

    @PutMapping("/interview-student")
    @Operation(summary = "修改报名学生")
    public ResultVO<Void> update(@RequestBody InterviewStudent stu) {
        adminService.updateInterviewStudent(stu);
        return ResultVO.success();
    }

    @DeleteMapping("/interview-student/{id}")
    @Operation(summary = "删除报名学生")
    public ResultVO<Void> delete(@PathVariable Integer id) {
        adminService.deleteInterviewStudent(id);
        return ResultVO.success();
    }

    @GetMapping("/interview-results")
    @Operation(summary = "查看所有学生面试结果")
    public ResultVO<List<InterviewResultVO>> interviewResults() {
        return ResultVO.success(adminService.listInterviewResults());
    }


}
