package com.slcp.devops.controller.admin;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.devops.constant.DevOpsConstant;
import com.slcp.devops.dto.UserDTO;
import com.slcp.devops.entity.*;
import com.slcp.devops.service.*;
import com.slcp.devops.api.Result;
import com.slcp.devops.utils.JedisCacheUtil;
import com.slcp.devops.utils.QiniuUtils;
import com.slcp.devops.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author: Slcp
 * @description: 聚合根
 * @create: 2022-06-28 13:59:01
 **/
@RestController
@RequestMapping("/sys")
@AllArgsConstructor
@Api(value = "聚合根", tags = "聚合根")
public class AggregateController {

    private final IAdminService adminService;
    private final IBlogService blogService;
    private final IReportService reportService;
    private final ITypeService typeService;
    private final ITagService tagService;
    private final ITagBlogRelService tagBlogRelService;
    private final JedisCacheUtil jedisCacheUtil;


    @GetMapping("/blog/add/color")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "tagList", notes = "获取所有分类以及标签")
    public Result<?> tagList(@RequestParam("id") String id) {
        Map<String, Object> map = MapUtil.newHashMap();
        map.put("tagList", tagService.tagList());
        map.put("typeList", typeService.typeList());
        map.put("editBlogInfo", blogService.blogInfo(id));
        return Result.data(map);
    }

    @PostMapping("/blog/add")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "add", notes = "添加文章")
    public Result<?> add(@RequestBody Map<String, Object> queryParam) {
        Blog blog = SqlWhereWrapper.convertParamsMapToObject(queryParam, Blog.class);
        if (ObjectUtil.isNotEmpty(blog.getId())) {
            //删除标签
            tagBlogRelService.removeTagByBlogId(blog.getId());
        }
        if (StringUtil.isBlank(blog.getFirstPicture())) {
            blog.setFirstPicture(QiniuUtils.uploadQiniu());
        }
        blogService.saveOrUpdate(blog);
        boolean flag = tagBlogRelService.saveTagBlog(queryParam.get("tagIds"), blog.getId());

        jedisCacheUtil.remove("query.cache:getFirstPageBlogs.f4c35f3263bcecb3375ed76c918140aa");
        return Result.condition(flag);
    }

    @DeleteMapping("/blog/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_DELETE, value = "deleteBlog", notes = "删除分类")
    public Result<Blog> deleteBlog(@PathVariable("id") Long id) {
        //删除图片
        StringUtil.delPicture(blogService.getById(id).getFirstPicture());
        //删除文章
        blogService.removeById(id);
        //删除标签
        tagBlogRelService.removeTagByBlogId(id);
        return Result.success("删除成功");
    }

    @GetMapping("/reports/type")
    public Result<?> type() {
        return Result.data(reportService.listReport(), "获取报表成功");
    }

    @GetMapping("/users")
    public Result<IPage<UserDTO>> users(@RequestParam Map<String, Object> queryParam, Search search) {
        IPage<UserDTO> listInfoByPage = SqlWhereWrapper.getPage(search);
        return Result.data(adminService.listUserPage(listInfoByPage, (String)queryParam.get("query")));
    }

    @GetMapping("/abnormal")
    public Result<IPage<Blog>> abnormal(@RequestParam Map<String, Object> queryParam, Search search) {
        IPage<Blog> listInfoByPage = SqlWhereWrapper.getPage(search);
        return Result.data(blogService.page(listInfoByPage));
    }
}
