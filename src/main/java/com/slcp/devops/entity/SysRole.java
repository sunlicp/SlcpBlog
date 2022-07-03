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
 * (SysRole)实体类
 *
 * @author makejava
 * @since 2022-06-28 14:32:14
 */
@Data
@EqualsAndHashCode
@TableName(value = "sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = -47394923505707690L;
    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;
    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "rid")
    private Long rid;
    /**
     * 博客名称
     */
    @ApiModelProperty(value = "音乐名称")
    @TableField(value = "role_name")
    private String roleName;
    /**
     * 博客名称
     */
    @ApiModelProperty(value = "音乐名称")
    @TableField(value = "role_desc")
    private String roleDesc;
}