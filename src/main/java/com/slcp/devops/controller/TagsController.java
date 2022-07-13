package com.slcp.devops.controller;

import com.slcp.devops.dto.FirstPageDTO;
import com.slcp.devops.service.IBlogService;
import com.slcp.devops.utils.ColorUtil;
import com.slcp.devops.dto.TagDTO;
import com.slcp.devops.service.ITagService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    private IBlogService blogService;
    private final ITagService tagService;

    @GetMapping("/tags/{tid}")
    public String listType(@PathVariable Integer tid, Model model) {
        List<TagDTO> tags = tagService.getAllTag();

        if (tid > 0) {
            List<FirstPageDTO> blogs = blogService.getSearchBlogsByTagName(tid);
            model.addAttribute("pageInfo", blogs);
            model.addAttribute("pageInfoSize", blogs.size());
        }else {
            model.addAttribute("pageInfo", null);
            model.addAttribute("pageInfoSize", 0);
        }

        for (TagDTO tag : tags) {
            tag.setColor("background-color: " + ColorUtil.getRandColor());
        }
        model.addAttribute("tags", tags);
        //增添样式 id传回去，当前分类颜色会变深
        model.addAttribute("activeTypeId", tid);
        return "tags";
    }
}
