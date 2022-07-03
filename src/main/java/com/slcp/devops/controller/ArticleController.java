package com.slcp.devops.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.slcp.devops.dto.RecommendDTO;
import com.slcp.devops.service.BlogService;
import com.slcp.devops.entity.Type;
import com.slcp.devops.dto.FirstPageDTO;
import com.slcp.devops.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author: SunXiaoWei
 * @create: 2022/2/1210:49
 **/
@Controller
@AllArgsConstructor
@Api(value = "首页接口查询", tags = "首页接口查询")
@Slf4j
public class ArticleController {

    private final BlogService blogService;
    private final TypeService typeService;

    /**
     * 博客首页展示
     *
     * @param pageNum 当前页
     * @param model   对象
     * @return html
     */
    @GetMapping({"/", "/article"})
    public String article(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        List<FirstPageDTO> firstPageBlogs = blogService.getFirstPageBlogs(pageNum);
        List<RecommendDTO> recommendBlogs = ListUtil.empty();
        if (1 == pageNum) {
            recommendBlogs = blogService.getHotBlogs();
        }
        List<Type> types = typeService.getAllType();
        List<RecommendDTO> hotBlogs = blogService.getRecommendBlogs();
        List<RecommendDTO> topBlogs = blogService.getTopBlogs();

        PageInfo<FirstPageDTO> pageInfo = new PageInfo<>(firstPageBlogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", types);
        model.addAttribute("hotBlogs", hotBlogs);
        model.addAttribute("topBlogs", topBlogs);
        model.addAttribute("recommendedBlogs", recommendBlogs);
        return "article";
    }

    @GetMapping("/article/read/{id}")
    public String read(@PathVariable Integer id, Model model) {
        blogService.getBolgOneById(id);
        FirstPageDTO firstPageBlog = blogService.getFirstPageDTO(id);
        firstPageBlog.setFirstPicture("background-image: url(" + firstPageBlog.getFirstPicture() + ")");
        model.addAttribute("blog", firstPageBlog);
        return "read";
    }

    @RequestMapping("/search")
    public String search(@RequestParam("keywords") String keywords,
                         @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                         Model model) {
        List<FirstPageDTO> firstPageBlogs = blogService.getSearchBlogs(keywords);
        PageInfo<FirstPageDTO> pageInfo = new PageInfo<>(firstPageBlogs);
        List<RecommendDTO> hotBlogs = blogService.getRecommendBlogs();
        List<RecommendDTO> topBlogs = blogService.getTopBlogs();
        List<Type> types = typeService.getAllType();

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", types);
        model.addAttribute("hotBlogs", hotBlogs);
        model.addAttribute("topBlogs", topBlogs);
        model.addAttribute("keywords", keywords);
        return "search";
    }

    @GetMapping("/article/category/{name}")
    public String category(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @PathVariable String name, Model model) {
        try {
            if (pageNum != 1) {
                name = URLDecoder.decode(name, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<FirstPageDTO> firstPageBlogs = blogService.getCategoryBlogs(name);
        PageInfo<FirstPageDTO> pageInfo = new PageInfo<>(firstPageBlogs);
        //分类
        List<Type> types = typeService.getAllType();
        //热门文章
        List<RecommendDTO> hotBlogs = blogService.getRecommendBlogs();
        List<RecommendDTO> topBlogs = blogService.getTopBlogs();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", types);
        model.addAttribute("hotBlogs", hotBlogs);
        model.addAttribute("topBlogs", topBlogs);
        model.addAttribute("keywords", name);
        return "search";
    }
}
