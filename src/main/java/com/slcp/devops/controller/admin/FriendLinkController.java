package com.slcp.devops.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.devops.api.Result;
import com.slcp.devops.constant.DevOpsConstant;
import com.slcp.devops.entity.*;
import com.slcp.devops.service.IFriendLinkService;
import com.slcp.devops.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: Slcp
 * @date: 2020/9/23 10:15
 * @code: 一生的挚爱
 * @description: 后台友链控制器
 */
@RestController
@RequestMapping("/sys")
@AllArgsConstructor
@Api(value = "友链接口", tags = "友链接口")
public class FriendLinkController {

    private final IFriendLinkService friendLinkService;

    @GetMapping("/friend/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "getFriendById", notes = "根据主键获取信息")
    public Result<FriendLink> getFriendById(@PathVariable("id") Long id) {
        return Result.data(friendLinkService.getById(id));
    }

    @DeleteMapping("/friend/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_DELETE, value = "deleteFriend", notes = "删除友链")
    public Result<FriendLink> deleteFriend(@PathVariable("id") Long id) {
        return Result.condition(friendLinkService.removeById(id));
    }

    @PostMapping("/friend")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "addFriend", notes = "添加&修改友链")
    public Result<?> addFriend(@RequestBody FriendLink friendLink) {
        return friendLinkService.saveOrUpdateByFriend(friendLink);
    }

    @GetMapping("/friend")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "friendList", notes = "获取友链")
    public Result<IPage<FriendLink>> friendList(@RequestParam Map<String, Object> queryParam, Search search) {
        IPage<FriendLink> listInfoByPage = SqlWhereWrapper.getPage(search);
        String blogName = (String) queryParam.get("query");
        return Result.data(StringUtil.isBlank(blogName) ? friendLinkService.page(listInfoByPage) : friendLinkService.page(listInfoByPage, new QueryWrapper<FriendLink>().lambda().like(FriendLink::getBlogName, blogName)));
    }

    @GetMapping("/friend/{id}/{status}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "updFriendStatusById", notes = "修改友链状态")
    public Result<FriendLink> updFriendStatusById(@PathVariable("id") Long id, @PathVariable("status") Boolean status) {
        return Result.condition(friendLinkService.lambdaUpdate().eq(FriendLink::getId, id).set(FriendLink::getStatus, status).update());
    }
}

