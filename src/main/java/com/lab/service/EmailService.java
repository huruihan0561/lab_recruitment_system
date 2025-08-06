package com.lab.service;

public interface EmailService {
    void sendCode(String to, String code);
}
