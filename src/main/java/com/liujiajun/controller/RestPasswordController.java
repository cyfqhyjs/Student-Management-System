package com.liujiajun.controller;

import com.liujiajun.exception.CustomException;
import com.liujiajun.mapper.NoticeMapper;
import com.liujiajun.po.Notice;
import com.liujiajun.po.Userlogin;
import com.liujiajun.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RestPasswordController {

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    // 本账户密码重置
    @RequestMapping(value = "/passwordRest", method = {RequestMethod.POST})
    public String passwordRest(String oldPassword, String password1) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        Userlogin userlogin = userloginService.findByName(username);

        if (userlogin == null) {
            throw new CustomException("用户不存在");
        }

        // 尝试使用 BCrypt 验证旧密码
        try {
            if (!BCrypt.checkpw(oldPassword, userlogin.getPassword())) {
                throw new CustomException("旧密码不正确");
            }
        } catch (IllegalArgumentException e) {
            // 如果 BCrypt 验证失败，尝试直接比较明文密码
            if (!oldPassword.equals(userlogin.getPassword())) {
                throw new CustomException("旧密码不正确");
            }
        }

        // 对新密码进行加密处理
        String encryptedPassword = BCrypt.hashpw(password1, BCrypt.gensalt());

        // 更新密码
        userlogin.setPassword(encryptedPassword);
        userloginService.updateByName(username, userlogin);

        return "redirect:/logout";
    }


}