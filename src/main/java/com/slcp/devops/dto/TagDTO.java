package com.slcp.devops.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: SunXiaoWei
 * @create: 2022/2/1316:04
 **/
@Data
public class TagDTO implements Serializable {
    private static final long serialVersionUID = -2309782578272943999L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;

    private String tagName;
    private Integer sum;
    private String color;
}
