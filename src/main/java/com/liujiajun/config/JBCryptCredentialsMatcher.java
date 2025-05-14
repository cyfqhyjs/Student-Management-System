package com.liujiajun.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.mindrot.jbcrypt.BCrypt;

public class JBCryptCredentialsMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // 获取用户提交的密码
        String submittedPassword = new String((char[]) token.getCredentials());
        // 获取数据库中存储的加密密码
        String storedPassword = (String) info.getCredentials();

        try {
            // 尝试使用 BCrypt 验证加密密码是否匹配
            if (BCrypt.checkpw(submittedPassword, storedPassword)) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            // 如果 storedPassword 不是有效的 BCrypt 格式，忽略异常并继续处理
        }

        // 如果加密密码不匹配，直接比较明文密码是否相同
        return submittedPassword.equals(storedPassword);
    }
}