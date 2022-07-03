package com.slcp.devops.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author: Slcp
 * @date: 2020/9/22 12:48
 * @code: 一生的挚爱
 * @description: 友链实体类
 */
@Data
public class FriendLink {
    private Integer friendId;
    private String blogname;
    private String blogaddress;
    private String headImg;
    private String blogImg;
    private String description;
    private Date createTime;

}
