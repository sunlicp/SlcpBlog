package com.slcp.devops.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Slcp
 * @date: 2020/9/24 14:48
 * @code: 一生的挚爱
 * @description:
 */
@Controller
public class MusicShowController {

    @GetMapping("/music")
    public String music() {
        return "music";
    }

    @GetMapping("/fireworks")
    public String fireworks() {
        return "fireworks";
    }

    @GetMapping("/baidu")
    public String baidu() {
        return "baidu";
    }
}
