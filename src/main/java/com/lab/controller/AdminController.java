package com.lab.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lab.dto.AdminLoginDTO;
import com.lab.dto.UpdateInterviewResultDTO;
import com.lab.entity.InterviewResult;
import com.lab.entity.InterviewStudent;
import com.lab.entity.Student;
import com.lab.service.AdminService;
import com.lab.service.PasswordService;
import com.lab.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "管理员相关接口", description = "提供管理员管理学生、面试结果等功能")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public ResultVO<String> login(@Validated @RequestBody AdminLoginDTO dto,
                                  @RequestParam String kaptcha,
                                  HttpSession session) {
        String cache = redisTemplate.opsForValue().get("kaptcha:" + session.getId());
        if (!kaptcha.equalsIgnoreCase(cache)) {
            return ResultVO.fail("验证码错误");
        }
        redisTemplate.delete("kaptcha:" + session.getId());
        return ResultVO.success(adminService.login(dto));
    }


    @GetMapping("/students")
    @Operation(summary = "查看所有注册学生")
    public ResultVO<IPage<Student>> students(@RequestParam(defaultValue = "1") int current,
                                             @RequestParam(defaultValue = "10") int size) {
        return ResultVO.success(adminService.listStudents(current, size));
    }


    @GetMapping("/interview-students")
    @Operation(summary ="查看所有报名学生")
    public ResultVO<IPage<InterviewStudent>> interviewStudents(
            @RequestParam(defaultValue = "1") int current,
            @RequestParam(defaultValue = "10") int size) {
        return ResultVO.success(adminService.listInterviewStudents(current, size));
    }


    @GetMapping("/interview-results")
    @Operation(summary = "查看学生面试结果")
    public ResultVO<IPage<InterviewResult>> interviewResults(
            @RequestParam(defaultValue = "1") int current,
            @RequestParam(defaultValue = "10") int size) {
        return ResultVO.success(adminService.listInterviewResults(current, size));
    }


    @PostMapping("/add-student")
    @Operation(summary = "添加报名学生")
    public ResultVO<Void> add(@RequestBody InterviewStudent stu) {
        adminService.addInterviewStudent(stu);
        return ResultVO.success();
    }

    @PutMapping("/update-student")
    @Operation(summary = "修改报名学生")
    public ResultVO<Void> update(@Valid @RequestBody InterviewStudent stu) {
        adminService.updateInterviewStudent(stu);
        return ResultVO.success();
    }


    @PutMapping("/update-result")
    @Operation(summary = "修改学生面试结果")
    public ResultVO<Void> updateInterviewResult(@RequestBody @Valid UpdateInterviewResultDTO dto) {
        adminService.updateInterviewResult(dto);
        return ResultVO.success();
    }

    @DeleteMapping("/delete-student/{id}")
    @Operation(summary = "删除学生")
    public ResultVO<Void> deleteInterviewStudentByStudentId(@PathVariable("id") String studentId) {
        adminService.deleteInterviewStudent(studentId);
        return ResultVO.success();
    }


    @GetMapping("/search")
    @Operation(summary = "根据学生姓名进行模糊查询")
    public ResultVO<IPage<Student>> search(
            @RequestParam(defaultValue = "1") int current,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String name) {
        return ResultVO.success(adminService.searchStudentsByName(current, size, name));
    }


}
