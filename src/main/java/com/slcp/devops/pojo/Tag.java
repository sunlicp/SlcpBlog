package com.slcp.devops.pojo;

import lombok.Data;

/**
 * @program: devops-root
 * @description:
 * @author: SunXiaoWei
 * @create: 2022/2/1316:04
 **/
@Data
public class Tag {

    private Integer tagId;
    private String tagName;
    private Integer sum;
    private String color;
}
