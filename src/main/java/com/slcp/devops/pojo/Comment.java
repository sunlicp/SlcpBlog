package com.slcp.devops.pojo;

import com.slcp.devops.queryVo.DetailedBlog;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 11:42
 * @code: 一生的挚爱
 * @description: 评论实体类
 */
@Data
public class Comment {

    private String commentId;
    private String email;
    private String content;
    private String nickname;

    private String avatar;
    private Date createTime;

    private Long blogId;
    //父评论id 用来标识子评论属于哪个父评论的
    private String parentCommentId;
    private String parentNickname;
    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;//做扩展用，展示没有什么用，评论内容都存在了content字段中
    private boolean adminComment;

    private DetailedBlog blog;
}
