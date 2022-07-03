package com.slcp.devops.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 12:59
 * @code: 一生的挚爱
 * @description: 分类实体类
 */
@Data
public class Type {

    private Integer typeId;
    private String typeName;
    private Integer sum;

    private List<Blog> blogs = new ArrayList<>();

}
