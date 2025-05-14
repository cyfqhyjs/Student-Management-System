package com.liujiajun.controller;

import com.liujiajun.po.Userlogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    // 登录跳转
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUI() throws Exception {
        return "../../login";
    }

    // 登录表单处理
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) throws Exception {

        // 获取Subject单例对象
        Subject subject = SecurityUtils.getSubject();

        // 创建UsernamePasswordToken对象
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            // 调用Shiro的login方法进行登录
            subject.login(token);

            // 登录成功后，根据角色跳转到不同的页面
            if (subject.hasRole("admin")) {
                return "redirect:/admin/showStudent";
            } else if (subject.hasRole("teacher")) {
                return "redirect:/teacher/showCourse";
            } else if (subject.hasRole("student")) {
                return "redirect:/student/showCourse";
            }
        } catch (AuthenticationException e) {
            // 如果登录失败，将错误信息添加到redirectAttributes中
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
        }

        // 如果登录失败，返回登录页面
        return "redirect:/login";
    }
}