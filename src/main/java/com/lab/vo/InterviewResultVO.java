package com.lab.vo;

import lombok.Data;


import java.util.Date;

@Data
public class InterviewResultVO {
    private String studentId;
    private String studentName;
    private String direction; // 意向方向
    private String status; // 面试结果
    private Date interviewTime;
}