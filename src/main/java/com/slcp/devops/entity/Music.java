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
 * @author: SunXiaoWei
 * @description: 音乐
 * @create: 2022-06-10 09:49:58
 **/
@Data
@TableName(value = "blog_music")
public class Music implements Serializable {
    private static final long serialVersionUID = -5509782578272943999L;
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
    @ApiModelProperty(value = "音乐名称")
    @TableField(value = "music_name")
    private String musicName;
    /**
     * 博客名称
     */
    @ApiModelProperty(value = "博客名称")
    @TableField(value = "music_code")
    private String musicCode;
    /**
     * 博客名称
     */
    @ApiModelProperty(value = "博客名称")
    @TableField(value = "music_path")
    private String musicPath;
}
