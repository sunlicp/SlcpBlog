package com.slcp.devops.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.mapper.IUserMapper;
import com.slcp.devops.utils.MD5Utils;
import com.slcp.devops.entity.User;
import com.slcp.devops.service.IUserService;
import org.springframework.stereotype.Service;


/**
 * @author: Slcp
 * @date: 2020/9/22 13:40
 * @code: 一生的挚爱
 * @description:
 */
@Service
public class IUserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

    @Override
    public User checkUsernameAndPassword(String username, String password) {
        return this.lambdaQuery().eq(User::getUsername, username).eq(User::getPassword, MD5Utils.code(password)).one();
    }
}
