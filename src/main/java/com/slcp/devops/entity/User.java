package com.slcp.devops.entity;

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
import java.util.Date;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:00
 * @code: 一生的挚爱
 * @description: 用户实体类
 */
@Data
@EqualsAndHashCode
@TableName(value = "blog_user")
public class User implements Serializable {
    private static final long serialVersionUID = -2609782578272943999L;
    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long userId;
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
    @TableField(value = "password")
    private String password;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField(value = "email")
    private String email;
    /**
     * 身份
     */
    @ApiModelProperty(value = "身份")
    @TableField(value = "avatar")
    private String avatar;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @TableField(value = "type")
    private Integer type;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
