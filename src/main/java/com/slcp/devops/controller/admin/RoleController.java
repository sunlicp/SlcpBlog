package com.slcp.devops.controller.admin;

import com.slcp.devops.api.Result;
import com.slcp.devops.constant.DevOpsConstant;
import com.slcp.devops.entity.SysRole;
import com.slcp.devops.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Slcp
 * @description: 角色
 * @create: 2022-07-06 13:33:35
 **/
@RestController
@RequestMapping(value = "/sys")
@AllArgsConstructor
@Api(value = "角色接口", tags = "角色接口")
public class RoleController {

    private final IRoleService roleService;

    @GetMapping("/roles")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "roleList", notes = "获取所有角色")
    public Result<List<SysRole>> roleList() {
        return Result.data(roleService.list());
    }

    @DeleteMapping("/roles/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_DELETE, value = "deleteRole", notes = "根据角色id删除角色")
    public Result<List<SysRole>> deleteRole(@PathVariable("id") Long id) {
        return Result.condition(roleService.removeById(id));
    }

    @GetMapping("roles/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "getRoleById", notes = "根据角色id获取角色信息")
    public Result<SysRole> getRoleById(@PathVariable("id") Long id) {
        return Result.data(roleService.getById(id));
    }

    @PostMapping("/roles")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "addRole", notes = "添加&修改角色")
    public Result<List<SysRole>> addRole(@RequestBody SysRole role) {
        return Result.condition(roleService.saveOrUpdate(role));
    }

    @GetMapping("/roles1/{id}/{rid}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "updateRights", notes = "根据角色id修改角色权限")
    public Result<List<SysRole>> updateRights(@PathVariable("id") Long id, @PathVariable("rid") Long rid) {
        roleService.lambdaUpdate().eq(SysRole::getId, id).set(SysRole::getRightsId, rid).update();
        return Result.data(roleService.list());
    }

}
