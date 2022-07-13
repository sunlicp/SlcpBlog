package com.slcp.devops.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (SysAdmin)实体类
 *
 * @author makejava
 * @since 2022-06-28 14:24:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_admin")
public class SysAdmin extends BaseEntity<SysAdmin> implements Serializable {
    private static final long serialVersionUID = -99322056007168973L;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField(value = "password", select = false)
    private String password;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "rid")
    private Long rid;

    /**
     * 状态
     *
     */
    @ApiModelProperty(value = "状态")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "is_status")
    private boolean status;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField(value = "email")
    private String email;

    /**
     * 令牌
     */
    @ApiModelProperty(value = "令牌")
    @TableField(value = "token", fill = FieldFill.INSERT)
    private String token;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 上次登录地址
     */
    @ApiModelProperty(value = "上次登录地址")
    @TableField(value = "last_login_address")
    private String lastLoginAddress;

    /**
     * 上次登录时间
     */
    @ApiModelProperty(value = "上次登录时间")
    @TableField(value = "last_login_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;


}