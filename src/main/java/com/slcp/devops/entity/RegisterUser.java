package com.slcp.devops.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Slcp
 * @date: 2020/10/29 17:13
 * @code: 一生的挚爱
 * @description: 注册用户
 */
@Data
public class RegisterUser implements Serializable {
    private static final long serialVersionUID = -2209782578272943999L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String username;
    private String password;
    private String phone;
    private String code;
    private String email;

    /**
     * 0 表示没有激活 1 表示已经激活
     */
    private Integer flag;

}
