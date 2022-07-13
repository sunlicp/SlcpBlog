package com.slcp.devops.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.devops.api.Result;
import com.slcp.devops.constant.DevOpsConstant;
import com.slcp.devops.dto.BlogQueryDTO;
import com.slcp.devops.entity.*;
import com.slcp.devops.service.IBlogService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: Slcp
 * @date: 2020/9/22 14:29
 * @code: 一生的挚爱
 * @description: 后台博客管理控制器
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys")
@Slf4j
public class BlogController {

    private final IBlogService blogService;

    @GetMapping("/blog")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "blogList", notes = "获取博客列表")
    public Result<IPage<BlogQueryDTO>> blogList(@RequestParam Map<String, Object> queryParam, Search search) {
        IPage<BlogQueryDTO> listInfoByPage = SqlWhereWrapper.getPage(search);
        return Result.data(blogService.listBlogs(listInfoByPage, (String) queryParam.get("query")));
    }

    @GetMapping("/blog/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "getBlogById", notes = "根据主键获取信息")
    public Result<Blog> getBlogById(@PathVariable("id") Long id) {
        return Result.data(blogService.getById(id));
    }

    @GetMapping("/blog/{id}/{typeId}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "deleteBlog", notes = "删除分类")
    public Result<Blog> deleteBlog(@PathVariable("id") Long id, @PathVariable("typeId") Long typeId) {
        return Result.condition(blogService.lambdaUpdate().set(Blog::getTypeId, typeId).eq(Blog::getId, id).update());
    }

    @PostMapping("/blog")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "addBlog", notes = "添加&修改分类")
    public Result<Blog> addBlog(@RequestBody Blog blog) {
        return Result.condition(blogService.saveOrUpdate(blog));
    }
}
