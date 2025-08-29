package com.lab.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/options")
@Tag(name = "选项列表接口", description = "提供各种下拉选项数据")
public class OptionController {
    @GetMapping("/grades")
    @Operation(summary = "获取年级选项列表")
    public List<String> getGradeOptions() {
        return Arrays.asList("大一", "大二");
    }

    @GetMapping("/directions")
    @Operation(summary = "获取面试方向选项列表")
    public List<String> getDirectionOptions() {
        return Arrays.asList("嵌入式", "后端", "前端", "安卓");
    }

    @GetMapping("/interview-methods")
    @Operation(summary = "获取面试方式选项列表")
    public List<String> getInterviewMethodOptions() {
        return Arrays.asList("秋面", "霸面");
    }

    @GetMapping("/time")
    @Operation(summary = "获取可选面试时间选项列表")
    public List<String> availableTimes() {
        List<String> times = new ArrayList<>();
        //从今天起，每天两个时间段
        LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0);
        for (int i = 0; i < 2; i++) {
            LocalDateTime day = now.plusDays(i);
            times.add(day.withHour(19).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            times.add(day.withHour(20).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }
        return times;
    }

    @GetMapping("/interview-statuses")
    @Operation(summary = "获取面试状态选项列表（供管理员使用）")
    public List<String> getInterviewStatusOptions() {
        return Arrays.asList("未通过", "已通过");
    }
}
