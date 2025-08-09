package com.lab.service.impl;

import com.lab.service.EmailService;
import com.lab.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailUtil emailUtil;

    @Override
    public void sendCode(String to, String code) {
        emailUtil.sendVerificationCode(to, code);
    }
}