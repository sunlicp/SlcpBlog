package com.slcp.devops.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.slcp.devops.api.Result;
import com.slcp.devops.constant.DevOpsConstant;
import com.slcp.devops.entity.SysAdmin;
import com.slcp.devops.service.IAdminService;
import com.slcp.devops.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:31
 * @code: 一生的挚爱
 * @description: 管理员登录界面
 */
@RestController
@RequestMapping(value = "/sys")
@AllArgsConstructor
@Api(value = "用户接口", tags = "用户接口")
public class UserController {

    private final IAdminService adminService;

    @PostMapping("/login")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "login", notes = "登录")
    public Result<SysAdmin> login(@RequestBody SysAdmin admin) {
        SysAdmin sysAdmin = adminService.lambdaQuery().eq(SysAdmin::getUsername, admin.getUsername()).eq(SysAdmin::getPassword, MD5Utils.code(admin.getPassword())).one();
        if (ObjectUtil.isEmpty(sysAdmin)) {
            return Result.fail("用户名密码错误");
        }
        if (!sysAdmin.isStatus()) {
            return Result.fail("无效账号，请联系管理员！");
        }

        return Result.data(sysAdmin);
    }

    @GetMapping("/user/map/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "map", notes = "获取位置")
    public void map(@PathVariable("id") Long id) {
        //获取当前地理位置
        String map = HttpUtil.get(DevOpsConstant.CITY_JSON).split("\"")[11];
        adminService.lambdaUpdate().set(SysAdmin::getLastLoginAddress, map).set(SysAdmin::getLastLoginTime, LocalDateTime.now()).eq(SysAdmin::getId, id).update();
    }

    @PostMapping("/users")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "saveOrUpdateOfUser", notes = "用户保存修改")
    public Result<SysAdmin> saveOrUpdateOfUser(@RequestBody SysAdmin admin) {
        boolean flag = ObjectUtil.isEmpty(admin.getId());
        SysAdmin one = adminService.lambdaQuery().eq(SysAdmin::getUsername, admin.getUsername()).one();
        if (flag && ObjectUtil.isNotEmpty(one)) {
            return Result.fail("账号已存在~");
        }
        if (flag) {
            admin.setStatus(false);
            admin.setRid(2L);
            admin.setPassword(MD5Utils.code(admin.getPassword()));
        }
        return Result.condition(adminService.saveOrUpdate(admin));
    }

    @GetMapping("/users/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "getUserOfId", notes = "根据主键获取用户信息")
    public Result<SysAdmin> getUserOfId(@PathVariable("id") Long id) {
        return Result.data(adminService.getById(id));
    }

    @DeleteMapping("/users/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_DELETE, value = "deleteUser", notes = "删除用户")
    public Result<SysAdmin> deleteUser(@PathVariable("id") Long id) {
        return Result.condition(adminService.removeById(id));
    }


    @GetMapping("/roles/{id}/{rid}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "updRoleIdOfUser", notes = "根据用户id修改角色")
    public Result<SysAdmin> updRoleIdOfUser(@PathVariable("id") Long id, @PathVariable("rid") Long rid) {
        return Result.condition(adminService.lambdaUpdate().eq(SysAdmin::getId, id).set(SysAdmin::getRid, rid).update());
    }
}
