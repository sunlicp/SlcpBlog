package com.slcp.devops.service.impl;

import com.slcp.devops.mapper.RegisterUserMapper;
import com.slcp.devops.utils.Base64Utils;
import com.slcp.devops.utils.MessageUtils;
import com.slcp.devops.entity.RegisterUser;
import com.slcp.devops.service.RegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: Slcp
 * @date: 2020/10/29 15:30
 * @code: 一生的挚爱
 * @description:
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private RegisterUserMapper registerUserMapper;
    @Override
    public boolean sendCode(String phone, String template, Map code) {
        return MessageUtils.sendMessage(phone, template, code);
    }

    @Override
    public RegisterUser checkUsername(String username) {
        return registerUserMapper.checkUsername(username);
    }

    @Override
    public int saveRegisterUser(RegisterUser registerUser) {
        //默认没激活
        registerUser.setFlag(0);
        //密码加密
        registerUser.setPassword(Base64Utils.encode(registerUser.getPassword()));
        return registerUserMapper.saveRegisterUser(registerUser);
    }

    @Override
    public RegisterUser getRegisterUserByName(String registerUserName) {
        return registerUserMapper.getRegisterUserByName(registerUserName);
    }

    @Override
    public RegisterUser userLoginByUsername(String username, String password) {
        return registerUserMapper.userLoginByUsername(username, Base64Utils.encode(password));
    }

    @Override
    public RegisterUser userLoginByEmail(String username, String password) {
        return registerUserMapper.userLoginByEmail(username,password);
    }

    @Override
    public String forgetPassword(String username,String email) {
        return registerUserMapper.forgetPassword(username,email);
    }


}
