package com.slcp.devops.controller.admin;

import com.slcp.devops.api.Result;
import com.slcp.devops.constant.DevOpsConstant;
import com.slcp.devops.entity.SysRights;
import com.slcp.devops.service.IRightsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Slcp
 * @description: 权限
 * @create: 2022-07-06 14:05:52
 **/
@RestController
@RequestMapping(value = "/sys")
@AllArgsConstructor
@Api(value = "权限接口", tags = "权限接口")
public class RightsController {

    private final IRightsService rightsService;

    @GetMapping("/rights/list")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "rights", notes = "获取权限列表")
    public Result<List<SysRights>> rights() {
        return Result.data(rightsService.list());
    }

}
