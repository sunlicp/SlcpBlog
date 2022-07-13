package com.slcp.devops.dto;

import cn.hutool.core.collection.ListUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.slcp.devops.entity.TagBlogRel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:13
 * @code: 一生的挚爱
 * @description: 显示数据实体类
 */
@Data
public class BlogQueryDTO implements Serializable {
    private static final long serialVersionUID = -3209782578272943999L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;
    private String content;
    private String description;
    private String firstPicture;
    private Integer flag;

    private Boolean appreciation;
    private Boolean shareStatement;
    private Boolean published;
    private Boolean recommend;
    private Boolean top;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long views;
    private String updateTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;
    private String typeName;
    private TypeDTO type;

    private List<String> tagIds;
}
