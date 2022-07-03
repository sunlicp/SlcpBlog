package com.slcp.devops.controller;

import com.alibaba.fastjson.JSONObject;
import com.slcp.devops.service.AboutService;
import com.slcp.devops.service.IMusicService;
import com.slcp.devops.entity.Music;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 16:03
 * @code: 一生的挚爱
 * @description:
 */
@Controller
@AllArgsConstructor
@Api(value = "关于我接口查询", tags = "关于我展示接口查询")
public class AboutShowController {

    private final AboutService aboutService;
    private final IMusicService musicService;

    @GetMapping("/about")
    @ApiOperation(httpMethod = "GET", value = "about", notes = "文章、标签、分类 总计")
    public String about(Model model){
        JSONObject obj = aboutService.getQuantityStatisticsBySum();
        model.addAttribute("qStBySum", obj);
        List<Music> musicList = musicService.list();
        model.addAttribute("blogMusic", musicList.get(musicList.size()-1));

        return "about";
    }

    @GetMapping("/about/sChart")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "quantityStatistics", notes = "文章、标签、分类数量统计")
    public JSONObject quantityStatistics(){
        return aboutService.getQuantityStatistics();
    }
}
