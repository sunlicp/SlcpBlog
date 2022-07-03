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

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (SysReport)实体类
 *
 * @author makejava
 * @since 2022-06-28 14:32:14
 */
@Data
@EqualsAndHashCode
@TableName(value = "sys_report")
public class SysReport implements Serializable {
    private static final long serialVersionUID = -22378599510302818L;
    /**
     * 业务主表主键ID
     */
    @ApiModelProperty(value = "业务主表主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;
    /**
     * 用户数
     */
    @ApiModelProperty(value = "用户数")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "count")
    private Integer count;
    /**
     * 学院
     */
    @ApiModelProperty(value = "学院")
    @TableField(value = "academy")
    private String academy;
    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    @TableField(value = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

}

