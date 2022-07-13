package com.slcp.devops.controller;

import com.slcp.devops.dto.DetailedDTO;
import com.slcp.devops.entity.SysAdmin;
import com.slcp.devops.service.IBlogService;
import com.slcp.devops.dto.Comment;
import com.slcp.devops.service.CommentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/25 20:52
 * @code: 一生的挚爱
 */
@Controller
@Api(value = "评论接口查询", tags = "评论接口查询")
@Slf4j
public class CommentShowController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private IBlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    /**
     * 查询评论列表
     */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    /**
     * 新增评论
     * @param comment 评论
     * @param session s
     * @param model d
     * @return r
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session, Model model) {
        Long blogId = comment.getBlogId();
        SysAdmin user = (SysAdmin) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            //设置头像
            comment.setAvatar(avatar);
        }

        if (comment.getParentComment().getCommentId() != null) {
            comment.setParentCommentId(comment.getParentComment().getCommentId());
        }
        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    /**
     * 删除评论
     * @param blogId 博客id
     * @param id id
     * @param comment 评论
     * @param attributes a
     * @param model m
     * @return r
     */
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable Long id, Comment comment, RedirectAttributes attributes, Model model){
        commentService.deleteComment(comment,id);
        DetailedDTO detailedBlog = blogService.getDetailedBlogById(blogId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);


        model.addAttribute("blog", detailedBlog);
        model.addAttribute("comments", comments);
        return "blog";
    }


}
