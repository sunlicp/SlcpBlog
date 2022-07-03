package com.slcp.devops.dto;

import com.slcp.devops.entity.Tag;
import com.slcp.devops.entity.Type;
import com.slcp.devops.entity.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 11:37
 * @code: 一生的挚爱
 * @description: 博客实体类
 */
@Data
public class BlogDTO implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    private Integer id;
    private String title;
    private String content;
    private String firstPicture;
    private MultipartFile pictureUpload;
    private String flag;
    private Integer views;

    private Integer commentCount;

    private Boolean appreciation;
    private Boolean shareStatement;
    private Boolean commentabled;
    private Boolean published;
    private Boolean recommend;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Long typeId;
    private Long userId;
    private String description;
    private User user;
    private Type type;
    private List<Comment> comments;
    private List<Tag> tags;
}
