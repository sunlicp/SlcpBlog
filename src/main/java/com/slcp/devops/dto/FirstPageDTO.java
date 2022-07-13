package com.slcp.devops.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:23
 * @code: 一生的挚爱
 * @description: 博客首页数据实体类
 */
@Data
public class FirstPageDTO implements Serializable {
    private static final long serialVersionUID = -5209782578272943999L;
    /**
     * Blog
     */
    private Long id;

    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private Date createTime;
    private String description;
    private String content;
    /**
     * Type
     */
    private String typeName;
    private Long typeId;
    /**
     * User
     */
    private String nickname;
    private String avatar;
    private Long userId;
    /**
     * tags
     */
    private List<TagDTO> tags;
}
