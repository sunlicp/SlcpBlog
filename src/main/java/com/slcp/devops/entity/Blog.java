package com.slcp.devops.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 博文(Blog)表实体类
 *
 * @author makejava
 * @since 2022-07-05 10:24:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_blog")
public class Blog extends BaseEntity<Blog> implements Serializable {
    private static final long serialVersionUID = -2309782512312943999L;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "type_id")
    private Long typeId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @TableField(value = "title")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @TableField(value = "content")
    private String content;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @TableField(value = "description")
    private String description;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径")
    @TableField(value = "first_picture")
    private String firstPicture;

    /**
     * 赞赏
     */
    @ApiModelProperty(value = "赞赏")
    @TableField(value = "appreciation")
    private Boolean appreciation;

    /**
     * 标记
     */
    @ApiModelProperty(value = "标记")
    @TableField(value = "flag")
    private Integer flag;

    /**
     * 公开
     */
    @ApiModelProperty(value = "公开")
    @TableField(value = "published")
    private Boolean published;

    /**
     * 推荐
     */
    @ApiModelProperty(value = "推荐")
    @TableField(value = "recommend")
    private Boolean recommend;

    /**
     * 转载声明
     */
    @ApiModelProperty(value = "转载声明")
    @TableField(value = "share_statement")
    private Boolean shareStatement;

    /**
     * 置顶
     */
    @ApiModelProperty(value = "置顶")
    @TableField(value = "top")
    private Boolean top;
    //评论

    /**
     * 评论
     */
    @ApiModelProperty(value = "评论")
    @TableField(value = "comment")
    private Boolean comment;

    /**
     * 访问次数
     */
    @ApiModelProperty(value = "访问次数")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "views")
    private Long views;

    /**
     * 评论次数
     */
    @ApiModelProperty(value = "评论次数")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "comment_count")
    private Long commentCount;
}