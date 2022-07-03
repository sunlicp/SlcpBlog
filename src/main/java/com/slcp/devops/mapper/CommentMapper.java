package com.slcp.devops.mapper;

import com.slcp.devops.dto.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 10:58
 * @code: 一生的挚爱
 * @description:
 */
@Repository
public interface CommentMapper {

    /**
     * 根据创建时间倒序来排
     * @param blogId 博客id
     * @param blogParentId 博客父id
     * @return 数据
     */
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    /**
     * 查询一级回复
     * @param blogId 博客id
     * @param id id
     * @return 数据
     */
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") String id);

    /**
     * 查询二级回复
     * @param blogId 博客id
     * @param childId 子id
     * @return 数据
     */
    List<Comment> findByBlogIdAndReplayId(@Param("blogId") Long blogId,@Param("childId") String childId);

    /**
     * 保存
     * @param comment 评论
     */
    void saveComment(Comment comment);

    /**
     * 删除
     * @param id 主键
     */
    void deleteComment(Long id);
}
