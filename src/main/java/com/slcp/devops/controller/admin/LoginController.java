package com.slcp.devops.controller.admin;

import com.slcp.devops.entity.User;
import com.slcp.devops.service.IUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:31
 * @code: 一生的挚爱
 * @description: 管理员登录界面
 */
@Controller
@RequestMapping(value = "/admin")
@AllArgsConstructor
@Api(value = "登录接口", tags = "登录接口")
public class LoginController {

    private final IUserService IUserService;

    /**
     * 跳转到登录页面
     * @return R
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 跳转到首页
     * @return r
     */
    @GetMapping("/index")
    public String index(){
        return "admin/index";
    }

    /**
     * 登录检验
     * @param username 账号
     * @param password 密码
     * @param session s
     * @param attributes a
     * @return r
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = IUserService.checkUsernameAndPassword(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 注销功能
     * @param session s
     * @return r
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "admin/login";
    }

}
