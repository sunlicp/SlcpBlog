package com.slcp.devops.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * (TagBlogRel)实体类
 *
 * @author makejava
 * @since 2022-07-09 23:53:22
 */
@Data
@AllArgsConstructor
@TableName(value = "tag_blog_rel")
public class TagBlogRel implements Serializable {
    private static final long serialVersionUID = 384395818890264477L;
    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;

    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id")
    @TableField(value = "tid")
    private Long tagId;

    /**
     * 博客id
     */
    @ApiModelProperty(value = "博客id")
    @TableField(value = "bid")
    private Long blogId;


}

