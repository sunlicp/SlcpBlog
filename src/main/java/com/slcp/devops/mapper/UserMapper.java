package com.slcp.devops.mapper;

import com.slcp.devops.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:46
 * @code: 一生的挚爱
 * @description:
 */
@Repository
public interface UserMapper {

    User checkUsernameAndPassword(@Param("username") String username,@Param("password") String password);

}
