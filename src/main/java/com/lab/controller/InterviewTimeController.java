package com.lab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/time")
@Tag(name = "面试时间接口")
public class InterviewTimeController {

    @GetMapping("/available")
    @Operation(summary = "获取可选面试时间列表")
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

}
