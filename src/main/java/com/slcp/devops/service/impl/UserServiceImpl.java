package com.slcp.devops.service.impl;

import com.slcp.devops.mapper.UserMapper;
import com.slcp.devops.pojo.User;
import com.slcp.devops.service.UserService;
import com.slcp.devops.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:40
 * @code: 一生的挚爱
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUsernameAndPassword(String username, String password) {
        String code = MD5Utils.code(password);
        User user = userMapper.checkUsernameAndPassword(username, code);
        return user;
    }
}
