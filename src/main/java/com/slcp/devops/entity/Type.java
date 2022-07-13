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
 * 分类(TType)实体类
 *
 * @author makejava
 * @since 2022-07-07 09:06:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_type")
public class Type extends BaseEntity<Type> implements Serializable {
    private static final long serialVersionUID = -43086773059747942L;
    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;

    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名")
    @TableField(value = "name")
    private String name;

}

