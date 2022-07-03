package com.slcp.devops.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slcp.devops.dto.BlogQueryDTO;
import com.slcp.devops.dto.SearchDTO;
import com.slcp.devops.dto.ShowDTO;
import com.slcp.devops.utils.UploadQinniuyunUtil;
import com.slcp.devops.dto.BlogDTO;
import com.slcp.devops.entity.Tag;
import com.slcp.devops.entity.Type;
import com.slcp.devops.entity.User;
import com.slcp.devops.service.BlogService;
import com.slcp.devops.service.TagService;
import com.slcp.devops.service.TypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 14:29
 * @code: 一生的挚爱
 * @description: 后台博客管理控制器
 */
@Controller
@AllArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class BlogController {

    private final BlogService blogService;
    private final TypeService typeService;
    private final TagService tagService;

    @RequestMapping("/blogs")
    public String listBlogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10, "update_time desc");
        List<BlogQueryDTO> blogs = blogService.listBlogs();
        if (blogs != null) {
            PageInfo<BlogQueryDTO> pageInfo = new PageInfo<>(blogs);
            model.addAttribute("pageInfo", pageInfo);
        }
        List<Type> types = typeService.listTypes();
        model.addAttribute("types", types);
        return "admin/blogs";
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listTypes());
        model.addAttribute("blog", new BlogDTO());
        return "admin/blogs-input";
    }

    /**
     * 新增页面
     */
    @RequestMapping("/blogs/add")
    public String add(BlogDTO blog, RedirectAttributes attributes,
                      HttpSession session) throws IOException {
        blog.setUser((User) session.getAttribute("user"));
        blog.setTypeId(blog.getType().getTypeId());
        blog.setUserId(blog.getUser().getUserId());

        blog.setFlag(blog.getFlag());

        //上传url地址
        if (blog.getFirstPicture() != null) {
            blog.setFirstPicture(blog.getFirstPicture());
        }

        //上传图片
        MultipartFile pictureUpload = blog.getPictureUpload();
        String filePath = UploadQinniuyunUtil.uploadQiniuyun(pictureUpload);
        if (!StringUtils.isEmpty(filePath)) {
            blog.setFirstPicture(filePath);
        }

        int b = blogService.saveBlog(blog);

        if (b == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 跳转到博客编辑页面
     * @param id id
     * @param model m
     * @return r
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        ShowDTO blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();
        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags", tags);
        model.addAttribute("blog", blogById);
        model.addAttribute("types", allType);
        return "admin/blogs-input";
    }

    /**
     * 编辑博客
     * @param showBlog 博客
     * @param attributes a
     * @return r
     * @throws IOException io异常
     */
    @PostMapping("/blogs/{id}")
    public String editPost(@Valid ShowDTO showBlog, RedirectAttributes attributes) throws IOException {

        //上传图片
        MultipartFile pictureUpload = showBlog.getPictureUpload();
        String filePath = UploadQinniuyunUtil.uploadQiniuyun(pictureUpload);
        if (!StringUtils.isEmpty(filePath)) {
            showBlog.setFirstPicture(filePath);
        }

        int b = blogService.updateBlog(showBlog);
        if (b == 0) {
            attributes.addFlashAttribute("message", "修改失败");
        } else {
            attributes.addFlashAttribute("message", "修改成功");

        }
        return "redirect:/admin/blogs";
    }

    /**
     * 删除博客
     * @param id 主键
     * @param attributes a
     * @return r
     */
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes) {
        int b = blogService.deleteBlogById(id);
        if (b == 0) {
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
            //TODO博客新增，清除缓存
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 搜索博客
     * @param pageNum 当前页
     * @param searchBlog 博客
     * @param model m
     * @return r
     */
    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         SearchDTO searchBlog, Model model) {

        List<SearchDTO> blogs = blogService.searchBlogs(searchBlog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<SearchDTO> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }
}
