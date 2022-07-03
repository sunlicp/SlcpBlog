package com.slcp.devops.queryVo;

import com.slcp.devops.pojo.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:25
 * @code: 一生的挚爱
 * @description: 推荐博客数据实体类
 */
@Data
public class RecommendBlog {

    private String id;
    private String title;
    private String firstPicture;
    private String recommend;
    private String description;
    private String views;
    private String content;
    private String typeId;
    private String typeName;
    private Date createTime;
    private List<Tag> tags;
}
