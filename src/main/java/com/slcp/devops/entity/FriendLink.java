package com.slcp.devops.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;

/**
 * @author: Slcp
 * @date: 2020/9/22 12:48
 * @code: 一生的挚爱
 * @description: 友链实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "blog_friend")
public class FriendLink extends BaseEntity<FriendLink> implements Serializable {
    private static final long serialVersionUID = -1209782578272943999L;

    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;

    /**
     * 博客名称
     */
    @ApiModelProperty(value = "博客名称")
    @TableField(value = "blog_name")
    private String blogName;

    /**
     * 博客地址
     */
    @ApiModelProperty(value = "博客地址")
    @TableField(value = "blog_address")
    private String blogAddress;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @TableField(value = "head_img")
    private String headImg;

    /**
     * 博客图片
     */
    @ApiModelProperty(value = "博客图片")
    @TableField(value = "blog_img")
    private String blogImg;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @TableField(value = "description")
    private String description;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "is_status")
    private Boolean status;

}
