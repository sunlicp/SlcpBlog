package com.slcp.devops.dto;

import com.slcp.devops.entity.Type;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:13
 * @code: 一生的挚爱
 * @description: 显示数据实体类
 */
@Data
public class BlogQueryDTO implements Serializable {
    private static final long serialVersionUID = -3209782578272943999L;

    private Long id;
    private String title;
    private String updateTime;
    private Boolean recommend;
    private Boolean published;
    private Long typeId;
    private Type type;
}
