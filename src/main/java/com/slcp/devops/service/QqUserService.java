package com.slcp.devops.service;

import com.slcp.devops.entity.QqUser;

/**
 * @author: Slcp
 * @date: 2020/12/2 21:44
 * @code: 一生的挚爱
 * @description:
 */
public interface QqUserService {

    /**
     * 添加用户
     * @param QqUser 用户
     * @return 数值
     */
    int AddQqUser(QqUser QqUser);
}
