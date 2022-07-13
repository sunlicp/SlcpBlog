package com.slcp.devops.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.io.Serializable;

/**
 * 标签(TTag)实体类
 *
 * @author makejava
 * @since 2022-07-07 10:36:39
 */
@Data
@TableName(value = "t_tag")
public class Tag implements Serializable {
    private static final long serialVersionUID = -42415687668189199L;
    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;

    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名")
    @TableField(value = "name")
    private String name;
}

