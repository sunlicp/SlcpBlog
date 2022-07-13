package com.slcp.devops.mapper;

import com.slcp.devops.entity.QqUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: Slcp
 * @date: 2020/12/2 21:46
 * @code: 一生的挚爱
 * @description:
 */
@Mapper
@Repository
public interface QqUserMapper {

    /**
     * 添加
     * @param QqUser QQ用户
     * @return 数值
     */
    int AddQqUser(QqUser QqUser);
}
