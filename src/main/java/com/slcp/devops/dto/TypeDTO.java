package com.slcp.devops.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 12:59
 * @code: 一生的挚爱
 * @description: 分类实体类
 */
@Data
public class TypeDTO implements Serializable {
    private static final long serialVersionUID = -2509782578272943999L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;

    private String typeName;
    private Integer sum;

    private List<BlogDTO> blogs;

}
