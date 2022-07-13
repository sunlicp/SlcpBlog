package com.slcp.devops.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Slcp
 * @date: 2020/11/17 21:27
 * @code: 一生的挚爱
 * @description:
 */
@Data
public class QqInfo implements Serializable {
    private static final long serialVersionUID = -1609782578272943999L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;
    private String avatar;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 系统
     */
    private String system;


}
