package com.slcp.devops.entity;

import com.slcp.devops.dto.BlogDTO;
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
public class Type implements Serializable {
    private static final long serialVersionUID = -2509782578272943999L;

    private Long typeId;
    private String typeName;
    private Integer sum;

    private List<BlogDTO> blogs;

}
