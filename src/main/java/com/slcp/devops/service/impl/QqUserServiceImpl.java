package com.slcp.devops.service.impl;

import com.slcp.devops.mapper.QqUserMapper;
import com.slcp.devops.entity.QqUser;
import com.slcp.devops.service.QqUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Slcp
 * @date: 2020/12/2 21:45
 * @code: 一生的挚爱
 * @description:
 */
@Service
public class QqUserServiceImpl implements QqUserService {
    @Resource
    private QqUserMapper qqUserMapper;

    @Override
    public int AddQqUser(QqUser qqUser) {
        return qqUserMapper.AddQqUser(qqUser);
    }
}
