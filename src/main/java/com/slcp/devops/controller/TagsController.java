package com.slcp.devops.controller;

import com.github.pagehelper.PageInfo;
import com.slcp.devops.dto.FirstPageDTO;
import com.slcp.devops.service.BlogService;
import com.slcp.devops.utils.ColorUtil;
import com.slcp.devops.entity.Tag;
import com.slcp.devops.service.TagService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 13:17
 * @code: 一生的挚爱
 * @description:
 */
@Controller
@AllArgsConstructor
@Api(value = "标签接口查询", tags = "标签接口查询")
@Slf4j
public class TagsController {

    private BlogService blogService;
    private final TagService tagService;

    @GetMapping("/tags/{tid}")
    public String listType(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @PathVariable Integer tid, Model model) {
        List<Tag> tags = tagService.getAllTag();

        if (tid > 0) {
            List<FirstPageDTO> blogs = blogService.getSearchBlogsByTagName(tid);
            PageInfo<FirstPageDTO> pageInfo = new PageInfo<>(blogs);
            model.addAttribute("pageInfo", pageInfo);
        }else {
            model.addAttribute("pageInfo", new PageInfo<>());
        }

        for (Tag tag : tags) {
            tag.setColor("background-color: " + ColorUtil.getRandColor());
        }
        model.addAttribute("tags", tags);
        //增添样式 id传回去，当前分类颜色会变深
        model.addAttribute("activeTypeId", tid);
        return "tags";
    }
}
