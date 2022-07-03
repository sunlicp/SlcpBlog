package com.slcp.devops.controller;

import com.slcp.devops.service.IFriendLinkService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Slcp
 * @date: 2020/9/24 15:54
 * @code: 一生的挚爱
 * @description:
 */
@Controller
@AllArgsConstructor
@Api(value = "友链接口查询", tags = "友链接口查询")
public class FriendLinkShowController {

    private final IFriendLinkService friendLinkService;

    @GetMapping("/friends")
    public String friendLinks(Model model){
        model.addAttribute("friendLinks", friendLinkService.list());
        return "friends";
    }
}
