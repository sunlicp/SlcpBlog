package com.slcp.devops.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: SunXiaoWei
 * @create: 2022/2/1316:04
 **/
@Data
public class Tag implements Serializable {
    private static final long serialVersionUID = -2309782578272943999L;

    private Integer tagId;
    private String tagName;
    private Integer sum;
    private String color;
}
