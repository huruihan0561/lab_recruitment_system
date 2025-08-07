package com.lab.controller;

import com.lab.dto.LoginDTO;
import com.lab.dto.RegisterDTO;
import com.lab.entity.InterviewStudent;
import com.lab.mapper.InterviewStudentMapper;
import com.lab.service.StudentService;
import com.lab.vo.DirectionVO;
import com.lab.vo.InterviewResultVO;
import com.lab.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Tag(name = "学生相关接口", description = "提供学生面试报名、查询结果等功能")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private InterviewStudentMapper interviewStudentMapper;

    @PostMapping("/register")
    @Operation(summary = "学生注册")
    public ResultVO<Void> register(@Validated @RequestBody RegisterDTO dto) {
        studentService.register(dto);
        return ResultVO.success();
    }

    @PostMapping("/login")
    @Operation(summary = "学生登录")
    public ResultVO<String> login(@Validated @RequestBody LoginDTO dto) {
        return ResultVO.success(studentService.login(dto));
    }

    @PostMapping("/apply")
    @Operation(summary = "学生报名")
    public ResultVO<Void> apply(@RequestBody InterviewStudent dto) {
        interviewStudentMapper.insert(dto);
        return ResultVO.success();
    }

    @GetMapping("/directions")
    @Operation(summary = "查看四大方向")
    public ResultVO<List<DirectionVO>> directions() {
        return ResultVO.success(studentService.getDirections());
    }

    @GetMapping("/interview-result")
    @Operation(summary = "查看面试结果")
    public ResultVO<List<InterviewResultVO>> result(@RequestParam String studentId) {
        return ResultVO.success(studentService.getInterviewResult(studentId));
    }


}
