package com.slcp.devops.controller;

import com.slcp.devops.dto.PictureUploadDTO;
import com.slcp.devops.service.IPictureService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 15:59
 * @code: 一生的挚爱
 * @description: 前台照片墙控制器
 */
@Controller
@AllArgsConstructor
@Api(value = "前台照片墙接口查询", tags = "前台照片墙接口查询")
@Slf4j
public class PictureShowController {

    private final IPictureService pictureService;

    @GetMapping("/picture")
    public String pictures(Model model){
        model.addAttribute("pictures",pictureService.list());
        List<PictureUploadDTO> pictureUploads = pictureService.listUpload();
        model.addAttribute("pictureUploads",pictureUploads);
        return "picture";
    }
}
