package com.slcp.devops.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.slcp.devops.entity.SysAdmin;
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

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;
    private String content;
    private String firstPicture;
    private MultipartFile pictureUpload;
    private String flag;
    private Integer views;

    private Integer commentCount;

    private Boolean appreciation;
    private Boolean shareStatement;
    private Boolean comment;
    private Boolean published;
    private Boolean recommend;
    private Date createTime;
    private Date updateTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private String description;
    private SysAdmin user;
    private TypeDTO type;
    private List<Comment> comments;
    private List<TagDTO> Tags;
}
