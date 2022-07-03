package com.slcp.devops.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (SysAdmin)实体类
 *
 * @author makejava
 * @since 2022-06-28 14:24:49
 */
@Data
@EqualsAndHashCode
@TableName(value = "sys_admin")
public class SysAdmin implements Serializable {
    private static final long serialVersionUID = -99322056007168973L;
    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;
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
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "rid")
    private Long rid;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "status")
    private Boolean status;
    /**
     * 令牌
     */
    @ApiModelProperty(value = "令牌")
    @TableField(value = "token")
    private String token;
    /**
     * 真实名称
     */
    @ApiModelProperty(value = "真实名称")
    @TableField(value = "real_name")
    private String realName;

}

