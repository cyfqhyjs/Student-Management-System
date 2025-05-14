package com.liujiajun.config;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BcryptUtil {
    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt()); // 对明文密码进行加密
    }

    public static boolean match(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword); // 验证明文密码与加密后的密码是否匹配
    }
}