package com.slcp.devops.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:00
 * @code: 一生的挚爱
 * @description: 用户实体类
 */
@Data
public class User {

    private Integer userId;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;//普通用户，管理员
    private Date createTime;
    private Date updateTime;

}
