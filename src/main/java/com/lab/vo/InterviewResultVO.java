package com.lab.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewResultVO {
    private String studentId;
    private String studentName;
    private String major;
    private String direction; // 意向方向
    private String status; // 面试结果
    private LocalDateTime interviewTime;
}