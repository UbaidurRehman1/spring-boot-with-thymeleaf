package com.techno_soft.employee_directory.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This Encoder use the BCrypt Hash to encode password
 * @author ubaid
 */
public class Encoder {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return encoder.encode(password);
    }
}
