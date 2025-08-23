package com.lab.service;

import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
    boolean changeStudentPassword(String studentId, String oldPwd, String newPwd);
    boolean changeAdminPassword(String username, String oldPwd, String newPwd);
}
