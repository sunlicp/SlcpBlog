package com.slcp.devops.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.devops.api.Result;
import com.slcp.devops.constant.DevOpsConstant;
import com.slcp.devops.entity.Search;
import com.slcp.devops.entity.SqlWhereWrapper;
import com.slcp.devops.entity.Tag;
import com.slcp.devops.service.ITagService;
import com.slcp.devops.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: Slcp
 * @description: 标签
 * @create: 2022-07-07 10:21:54
 **/
@RestController
@RequestMapping("/sys")
@AllArgsConstructor
@Api(value = "标签接口", tags = "标签接口")
public class TagController {

    private final ITagService tagService;

    @GetMapping("/tag/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "getTagById", notes = "根据主键获取信息")
    public Result<Tag> getTagById(@PathVariable("id") Long id) {
        return Result.data(tagService.getById(id));
    }

    @DeleteMapping("/tag/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_DELETE, value = "deleteTag", notes = "删除标签")
    public Result<Tag> deleteTag(@PathVariable("id") Long id) {
        return Result.condition(tagService.removeById(id));
    }

    @PostMapping("/tag")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "addTag", notes = "添加&修改标签")
    public Result<Tag> addTag(@RequestBody Tag tag) {
        if (ObjectUtil.isEmpty(tag.getId()) && ObjectUtil.isNotEmpty(tagService.lambdaQuery().eq(Tag::getName, tag.getName()).one())) {
            return Result.fail("标签名已存在哦~");
        }
        return Result.condition(tagService.saveOrUpdate(tag));
    }

    @GetMapping("/tag")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "tagList", notes = "获取标签列表")
    public Result<IPage<Tag>> tagList(@RequestParam Map<String, Object> queryParam, Search search) {
        IPage<Tag> listInfoByPage = SqlWhereWrapper.getPage(search);
        String name = (String) queryParam.get("query");
        return Result.data(StringUtil.isBlank(name) ? tagService.page(listInfoByPage) : tagService.page(listInfoByPage, new QueryWrapper<Tag>().lambda().like(Tag::getName, name)));
    }
}
