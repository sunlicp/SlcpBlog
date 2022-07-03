package com.slcp.devops.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 11:37
 * @code: 一生的挚爱
 * @description: 博客实体类
 */
@Data
public class Blog {

    private Integer id;
    private String title;
    private String content;
    private String firstPicture;
    private MultipartFile pictureUpload;
    private String flag;
    private Integer views;

    private Integer commentCount;

    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer typeId;
    private Integer userId;
    private String description;
    private User user;
    private Type type;
    private List<Comment> comments = new ArrayList<>();
    private List<Tag> tags;
}
