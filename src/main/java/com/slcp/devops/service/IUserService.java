package com.slcp.devops.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slcp.devops.entity.User;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:40
 * @code: 一生的挚爱
 * @description:
 */
public interface IUserService extends IService<User> {
    /**
     * 校验
     * @param username 账号
     * @param password 密码
     * @return 用户
     */
    User checkUsernameAndPassword(String username, String password);
}
