package com.slcp.devops.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Slcp
 * @date: 2020/12/2 21:22
 * @code: 一生的挚爱
 * @description:
 */
@Data
public class QqUser implements Serializable {
    private static final long serialVersionUID = -2109782578272943999L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    private String nickname;
    private String gender;
    private String avatar;
    /**
     * 访问令牌 三个月过期
     */
    private String accessToken;
    /**
     * 过去时间
     */
    private Date tokenExpireIn;
    /**
     * 用户唯一标识
     */
    private String openId;
    private Date date;
}
