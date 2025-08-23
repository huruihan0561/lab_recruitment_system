package com.lab.vo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminStudentVO {
    private Integer id;
    private String studentId;
    private String name;
    private String status;
}
