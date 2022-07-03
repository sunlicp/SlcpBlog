package com.slcp.devops.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author: Slcp
 * @date: 2020/12/2 21:22
 * @code: 一生的挚爱
 * @description:
 */
@Data
public class QqUser {
    private Integer id;
    private String nickname;
    private String gender;
    private String avatar;
    private String accessToken;
    private Date tokenExpireIn;
    private String openId;

    private Date date;
}
