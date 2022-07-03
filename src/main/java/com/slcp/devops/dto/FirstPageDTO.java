package com.slcp.devops.dto;

import com.slcp.devops.entity.Tag;
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
    private Integer id;
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
    private String typeId;
    /**
     * User
     */
    private String nickname;
    private String avatar;
    private String userId;
    /**
     * tags
     */
    private List<Tag> tags;
}
