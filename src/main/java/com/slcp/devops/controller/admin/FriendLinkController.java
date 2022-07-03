package com.slcp.devops.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slcp.devops.entity.FriendLink;
import com.slcp.devops.service.IFriendLinkService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/23 10:15
 * @code: 一生的挚爱
 * @description: 后台友链控制器
 */
@Controller
@RequestMapping("/admin")
@AllArgsConstructor
@Api(value = "友链后台", tags = "友链后台")
public class FriendLinkController {

    private final IFriendLinkService friendLinkService;

    /**
     * 查询友链信息
     * @param pageNum 当前页
     * @param model 对象
     */
    @GetMapping("/friendLinks")
    public String listFriendLink(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){
        PageHelper.startPage(pageNum,10);
        List<FriendLink> list = friendLinkService.list();
        PageInfo<FriendLink> pageInfo = PageInfo.of(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/friendLinks";
    }

    /**
     * 跳转到友链编辑页面
     */
    @GetMapping("/friendLinks/input")
    public String input(Model model){
        model.addAttribute("friendLink",new FriendLink());
        return "admin/friendLinks-input";
    }

    /**
     * 保存友链信息
     *
     */
    @PostMapping("/friendLinks")
    public String post(@Valid FriendLink friendLink, RedirectAttributes attributes){
        FriendLink f1 = friendLinkService.lambdaQuery().eq(FriendLink::getBlogAddress, friendLink.getBlogAddress()).one();
        if (ObjectUtil.isNotEmpty(f1)){
            attributes.addFlashAttribute("message","友链重复,已存在该朋友呦!");
            return "redirect:/admin/friendLinks/input";
        }
        friendLinkService.saveFriendLink(friendLink);
        return "redirect:/admin/friendLinks";
    }

    /**
     * 跳转到编辑友链页面
     */
    @GetMapping("/friendLinks/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("friendLink", friendLinkService.getById(id));
        return "admin/friendLinks-input";
    }

    /**
     * 编辑保存
     * @param friendLink 友链
     * @return r
     */
    @PostMapping("/friendLinks/{id}")
    public String editPost(@Valid FriendLink friendLink, @PathVariable String id){
        friendLinkService.saveFriendLink(friendLink);
        return "redirect:/admin/friendLinks";
    }

    /**
     * 删除友链
     */
    @GetMapping("/friendLinks/{id}/delete")
    public String deleteFriendLink(@PathVariable Long id,RedirectAttributes attributes){
        friendLinkService.removeById(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/friendLinks";
    }

}
