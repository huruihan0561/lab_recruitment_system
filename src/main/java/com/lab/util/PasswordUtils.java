package com.lab.util;


import cn.hutool.crypto.digest.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {
    public static String encode(String raw) {
        return BCrypt.hashpw(raw, BCrypt.gensalt());
    }
    public static boolean matches(String raw, String encoded) {
        return BCrypt.checkpw(raw, encoded);
    }
}