package com.lab.util;

import java.util.regex.Pattern;

public class ValidatorUtil {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");

    public static void validatePhone(String phone) {
        if (!PHONE_PATTERN.matcher(phone).matches())
            throw new IllegalArgumentException("手机号格式错误");
    }
}
    