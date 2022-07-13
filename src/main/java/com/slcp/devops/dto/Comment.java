package com.slcp.devops.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 11:42
 * @code: 一生的挚爱
 * @description: 评论实体类
 */
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = -1109782578272943999L;

    private String commentId;
    private String email;
    private String content;
    private String nickname;

    private String avatar;
    private Date createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long blogId;
    /**
     * 父评论id 用来标识子评论属于哪个父评论的
     */
    private String parentCommentId;
    private String parentNickname;
    /**
     * 回复评论
     */
    private List<Comment> replyComments;
    /**
     * 做扩展用，展示没有什么用，评论内容都存在了content字段中
     */
    private Comment parentComment;
    private Boolean adminComment;

    private DetailedDTO blog;
}
