package com.slcp.devops.service;

import com.slcp.devops.pojo.User;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:40
 * @code: 一生的挚爱
 * @description:
 */
public interface UserService {
    User checkUsernameAndPassword(String username, String password);
}
