package com.slcp.devops.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.slcp.devops.utils.*;
import com.slcp.devops.entity.RegisterUser;
import com.slcp.devops.service.RegisterService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author: Slcp
 * @date: 2020/10/28 9:16
 * @code: 一生的挚爱
 * @description:
 */
@Controller
@CrossOrigin
@RequestMapping("/userLoginAndRegister")
@AllArgsConstructor
@Api(value = "登录接口", tags = "登录接口")
@Slf4j
public class UserLoginAndRegisterController {

    private final RegisterService registerService;
    private final EmailUtils emailUtils;
    private final JedisCacheUtil jedisCacheUtil;

    @GetMapping("/userLogin")
    public String userLogin() {
        return "userLogin";
    }

    @PostMapping("/userLogin")
    public String userLogin(String username, String password,
                            HttpSession session,
                            RedirectAttributes attributes) {
        RegisterUser registerUser;
        //判断是否邮箱登录
        if ("username".contains("@")) {
            registerUser = registerService.userLoginByEmail(username, password);
        } else {
            //判断是否用户名登录
            registerUser = registerService.userLoginByUsername(username, password);
        }

        if (registerUser == null) {
            attributes.addFlashAttribute("loginMsg", "用户名密码错误，请重新输入");
            return "redirect:/userLoginAndRegister/userLogin";
        }
        session.setAttribute("registerUser", username);
        //登入成功跳转到留言板页面
        return "redirect:/message";
    }

    @PostMapping("/checkUserName")
    @ResponseBody
    public String checkUserName(@RequestParam("username") String username) {    //接收不到参数
        System.out.println("去后台查询数据库信息" + username);
        RegisterUser registerUser = registerService.checkUsername(username);
        if (registerUser != null) {
            return "0";
        } else {
            return "1";
        }
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(RegisterUser registerUser, HttpSession session,
                           RedirectAttributes attributes) throws MessagingException {
        //增加严谨性(用户提交时在对用户名和是否为空再校验一遍)
        String registerUserName = registerUser.getUsername();
        String registerPassword = registerUser.getPassword();
        if (ObjectUtil.isEmpty(registerUser)) {
            return "3";
        } else {
            RegisterUser checkUsername = registerService.checkUsername(registerUserName);
            if (checkUsername != null) {
                return "2";
            } else {
                //做保存注册用户操作
                int b = registerService.saveRegisterUser(registerUser);
                if (b == 0) {
                    attributes.addFlashAttribute("failMsg", "注册测试失败，请重新注册");
                    return "0";
                } else {
                    //注册成功后删除原来的的验证码防止暴力破解
                    session.removeAttribute("code");
                    //存入session中用于邮件显示加密前密码
                    session.setAttribute("registerUserName", registerUserName);
                    session.setAttribute("registerPassword", registerPassword);
                    return "1";
                }
            }
        }
    }

    @GetMapping("/registerSuccess")
    public String registerSuccess(HttpSession session, Model model) throws MessagingException {
        String registerUserName = (String) session.getAttribute("registerUserName");
        //根据注册时存入session的用户名查询用户信息
        RegisterUser registerUser = registerService.getRegisterUserByName(registerUserName);
        //取出未加密前的密码
        String registerPassword = (String) session.getAttribute("registerPassword");
        registerUser.setPassword(registerPassword);

        //注册成功后进行邮件发送
        emailUtils.sendMimeMessage(registerUser);
        model.addAttribute("registerUser", registerUser);
        return "registerSuccess";
    }


    /**
     * 生成验证码
     * @param session s
     * @param response r
     * @throws IOException io异常
     */
    @GetMapping("/code")
    public void getVerification(HttpSession session, HttpServletResponse response) throws IOException {
        System.out.println("正在生成验证码中----");
        //生成验证码 调用工具类生成
        String securityCode = VCodeUtils.getSecurityCode();
        //生成验证码图片
        BufferedImage image = VCodeUtils.createImage(securityCode);
        //存入session
        session.setAttribute("code", securityCode);
        //响应图片
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    @PostMapping("/checkCode")
    @ResponseBody
    public String checkCode(String code, HttpSession session) {
        String vcode = (String) session.getAttribute("code");
        if (vcode.equals(code)) {
            return "1";
        } else {
            return "0";
        }
    }

    @ResponseBody
    @PostMapping("/sendCode")
    public String sendCode(String phone){
        String code = jedisCacheUtil.getString(phone);
        if (!StringUtil.isEmpty(code)){
            return "验证码已存在";
        }else {
            code = UUID.randomUUID().toString().substring(0, 4);
            System.out.println("获取到电话号码"+phone+"验证码为"+code);
            HashMap<String, Object> map = MapUtil.newHashMap();
            map.put("code",code);
            boolean sendCode = registerService.sendCode(phone, "SMS_205128031", map);
            if (sendCode){
                jedisCacheUtil.putString(phone, code, 5);
                return "success";
            }else {
                return "fail";
            }
        }

    }


    /**
     * 忘记密码功能进行跳转
     * @return r
     */
    @GetMapping("/forgetPassword")
    public String requestEmail() {
        return "forgetPassword";
    }

    @PostMapping("/forgetPassword")
    public String forgetPassword(String username, String email, Model model) throws MessagingException {
        String password = registerService.forgetPassword(username, email);
        if (password != null) {
            //查询到密码进行解密操作
            String decodePassword = Base64Utils.decode(password);
            //发送邮件
            emailUtils.sendMimeMessageRetrievePassword(username, email, decodePassword);
            model.addAttribute("decodePassword", "密码已发送至您的邮箱,请您注意查收");
        } else {
            model.addAttribute("decodePassword", "用户名或邮箱错误,请重新输入");
        }
        return "forgetPassword";
    }
}

