package com.slcp.devops.controller;

import com.github.pagehelper.PageInfo;
import com.slcp.devops.dto.DetailedDTO;
import com.slcp.devops.service.BlogService;
import com.slcp.devops.dto.Comment;
import com.slcp.devops.dto.FirstPageDTO;
import com.slcp.devops.service.CommentService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 9:45
 * @code: 一生的挚爱
 * @description:
 */
@Controller
@AllArgsConstructor
@Api(value = "友链接口查询", tags = "友链接口查询")
public class IndexShowController {

    private final BlogService blogService;
    private final CommentService commentService;

    /**
     * 搜索博客
     * @param pageNum 当前页
     * @param query 添加
     * @param model 对象
     * @return 返回
     */
    @PostMapping("/search")
    public String search(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(name = "query") String query, Model model) {
        List<FirstPageDTO> blogs = blogService.getSearchBlogs(query);
        PageInfo<FirstPageDTO> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        return "search";
    }


    /**
     * 跳转到博客详情页面
     * @param id 主键
     * @param model 对象
     * @return 博客信息
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        DetailedDTO blog = blogService.getDetailedBlogById(id);
        model.addAttribute("blog", blog);
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);
        return "blog";
    }


    /**
     * 查询底部栏博客信息
     * @param model 对象
     * @return 数据
     */
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model) {
        String blogTotal = String.valueOf(blogService.getBlogTotal());
        model.addAttribute("blogTotal", blogTotal);
        String blogViewTotal = String.valueOf(blogService.getBlogViewTotal());
        model.addAttribute("blogViewTotal", blogViewTotal);
        try {
            model.addAttribute("blogDays", getDays("2019-7-10"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "article :: blogMessage";
    }

    public static String getDays(String date) throws ParseException {
        return (System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime())/86400000+" days";
    }
}
