package com.slcp.devops.controller;

import com.alibaba.fastjson.JSONObject;
import com.slcp.devops.service.ArchiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author: Slcp
 * @date: 2020/9/24 14:27
 * @code: 一生的挚爱
 * @description:
 */
@Controller
@AllArgsConstructor
@Api(value = "快速报价接口查询", tags = "快速报价接口查询")
@Slf4j
public class ArchiveShowController {

    private final ArchiveService archiveService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("blogs", archiveService.getAll());
        model.addAttribute("years", archiveService.getYear());
        return "archives";
    }

    @RequestMapping("/archives/sQuByTime")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "articlesNum", notes = "获取一年每月的文章数量")
    public JSONObject articlesNum() {
        return archiveService.getMon();
    }
}
