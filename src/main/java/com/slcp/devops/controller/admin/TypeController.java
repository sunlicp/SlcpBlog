package com.slcp.devops.controller.admin;

import com.github.pagehelper.PageInfo;
import com.slcp.devops.entity.Type;
import com.slcp.devops.service.TypeService;
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
 * @date: 2020/9/23 8:51
 * @code: 一生的挚爱
 * @description: 分类控制器
 */

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
@Api(value = "类型接口查询", tags = "类型接口查询")
public class TypeController {

    private final TypeService typeService;

    @GetMapping("/types")
    public String listTypes(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        //按照排序字段 倒叙排序
        List<Type> types = typeService.listTypes();

        if (types != null) {
            PageInfo<Type> pageInfo = new PageInfo<>(types);
            model.addAttribute("pageInfo", pageInfo);
        }
        return "admin/types";
    }

    /**
     * 跳转到输入页面
     * @param model m
     * @return r
     */
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /**
     * 新增分类
     * @param type 类型
     * @param attributes a
     * @return r
     */
    @PostMapping("/types")
    public String post(@Valid Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getTypeName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复分类");
            return "redirect:/admin/types/input";
        }

        int b = typeService.saveType(type);
        if (b == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 跳转到分类编辑页面
     * @param id 主键
     * @param model m
     * @return r
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable String id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    /**
     * 编辑保存
     * @param type 类型
     * @param attributes a
     * @return r
     */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, RedirectAttributes attributes, @PathVariable String id) {
        int t = typeService.updateType(type);
        if (t == 0) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 删除分类
     * @param id 主键
     * @param attributes a
     * @return r
     */
    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Long id, RedirectAttributes attributes) {
        int b = typeService.deleteById(id);
        if (b==0) {
            attributes.addFlashAttribute("message", "删除失败,有博客绑定该分类！");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/types";
    }
}
