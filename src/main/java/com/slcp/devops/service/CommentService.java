package com.slcp.devops.service;

import com.slcp.devops.dto.Comment;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 10:57
 * @code: 一生的挚爱
 * @description:
 */
public interface CommentService {
    /**
     * 查询评论列表
     * @param id 主键
     * @return 数据
     */
    List<Comment> listCommentByBlogId(Long id);

    /**
     * 保存
     * @param comment 评论
     */
    void saveComment(Comment comment);

    /**
     * 删除
     * @param comment 评论
     * @param id 主键
     */
    void deleteComment(Comment comment, Long id);
}
