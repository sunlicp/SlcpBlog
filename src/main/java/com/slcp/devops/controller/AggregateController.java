package com.slcp.devops.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.devops.entity.*;
import com.slcp.devops.service.*;
import com.slcp.devops.api.Result;
import com.slcp.devops.utils.MD5Utils;
import com.slcp.devops.utils.TokenUtils;
import io.swagger.annotations.Api;
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
@CrossOrigin
@Api(value = "系统聚合根", tags = "管理系统")
public class AggregateController {

    private IAdminService adminService;
    private IAttendanceService attendanceService;
    private IRoleService roleService;
    private IRightsService rightsService;
    private IReportService reportService;

    @RequestMapping("add")
    public String add(@RequestParam Map<String, Object> queryParam) {
        SysAdmin byName = adminService.lambdaQuery().eq(SysAdmin::getRealName, queryParam.get("realName")).one();
        if (ObjectUtil.isEmpty(byName)) {
            return "add.jsp";
        }
        SysAttendance sysAttendance = SqlWhereWrapper.convertParamsMapToObject(queryParam, SysAttendance.class);
        sysAttendance.setUid(byName.getId());
        attendanceService.save(sysAttendance);
        return "main";
    }

    @PostMapping("/login")
    public Result<SysAdmin> login(@RequestBody SysAdmin admin) {
        SysAdmin sysAdmin = adminService.lambdaQuery().eq(SysAdmin::getUsername, admin.getUsername()).eq(SysAdmin::getPassword, MD5Utils.code(admin.getPassword())).one();
        if (ObjectUtil.isNotEmpty(sysAdmin)) {
            return Result.success("登录成功");
        }
        return Result.fail("用户名密码错误");
    }

    @DeleteMapping("/abnormal/{id}/{uid}")
    public Result<List<SysAttendance>> deleteUser(@PathVariable("id") Integer id, @PathVariable("uid") Integer uid) {
        attendanceService.removeById(id);
        return Result.data(attendanceService.list());
    }

    @PostMapping("/abnormal")
    public Result<List<SysAttendance>> insert(@RequestBody SysAttendance attendance) {
        attendanceService.save(attendance);
        return Result.data(attendanceService.list());
    }

    @GetMapping("/reports/type")
    public Result type() {
        List<SysReport> reports = reportService.list();
        //不同的学院容器
        Set<String> set = new HashSet<>();
        //不同时间容器
        Set time = new TreeSet();
        for (SysReport report : reports) {
            //将不同的学院进行存储
            String academy = report.getAcademy();
            set.add(academy);
            //将不同的时间进行存储
            time.add(report.getDate());
        }
        //封装数据的容器
        Map map = new HashMap();
        //提交数据给用户
        HashMap legend = new HashMap();
        legend.put("data", set);
        map.put("legend", legend);


        //存储每个学院每天总数
        List series = new ArrayList();

        for (String s : set) {
            series.add(map(reports, s));
        }
        map.put("series", series);

        //获取所有时间
        HashMap hashMap = new HashMap();
        hashMap.put("boundaryGap", false);
        hashMap.put("data", time);
        List xAxis = new ArrayList();
        xAxis.add(hashMap);
        map.put("xAxis", xAxis);
        return Result.data(map, "获取报表成功");
    }

    public HashMap<String, Object> map(List<SysReport> reports, String academy) {
        List<Integer> list = new ArrayList<>();
        for (SysReport report : reports) {
            if (academy.equals(report.getAcademy())) {
                list.add(report.getCount());
            }
        }
        //将相同学院不同时间的出勤异常数量分别进行保存
        HashMap<String, Object> hashMap = MapUtil.newHashMap();
        hashMap.put("areaStyle", "");
        hashMap.put("data", list);
        hashMap.put("name", academy);
        hashMap.put("stack", "总数");
        hashMap.put("type", "line");
        return hashMap;
    }

    @DeleteMapping("/roles/{id}")
    public Result<List<SysRole>> deleteRole(@PathVariable("id") Integer id) {
        //调用service层
        boolean flag = roleService.removeById(id);
        if (flag) {
            return Result.data(roleService.list(), "删除成功");
        }
        return Result.fail("删除失败");
    }

    @GetMapping("roles/{id}")
    public Result<SysRole> getRole(@PathVariable("id") Long id) {
        return Result.data(roleService.getById(id));
    }

    @PostMapping("/roles")
    public Result<List<SysRole>> addRole(@RequestBody SysRole role) {
        boolean flag = roleService.saveOrUpdate(role);
        //判断是否添加成功
        if (flag) {
            return Result.data(roleService.list(), "修改成功");
        }
        return Result.fail("修改失败");
    }

    @GetMapping("/roles1/{id}/{rid}")
    public Result<List<SysRole>> updateRights(@PathVariable("id") Long id, @PathVariable("rid") Long rid) {
        roleService.lambdaUpdate().eq(SysRole::getId, id).set(SysRole::getRid, rid).update();
        return Result.data(roleService.list());
    }

    @GetMapping("/rights/list")
    public Result<List<SysRights>> rights() {
        return Result.data(rightsService.list());
    }

    @GetMapping("/roles")
    public Result<List<SysRole>> roles(){
        return Result.data(roleService.list());
    }

    @GetMapping("/users")
    public Result<IPage<SysAdmin>> users(@RequestParam Map<String, Object> queryParam, Search search) {
        IPage<SysAdmin> listInfoByPage = SqlWhereWrapper.getPage(search);
        return Result.data(adminService.page(listInfoByPage));
    }

    @GetMapping("/abnormal")
    public Result<IPage<SysAttendance>> abnormal(@RequestParam Map<String, Object> queryParam, Search search) {
        IPage<SysAttendance> listInfoByPage = SqlWhereWrapper.getPage(search);
        return Result.data(attendanceService.page(listInfoByPage));
    }

    @PostMapping("/users")
    public Result<SysAdmin> user(@RequestBody SysAdmin admin) {
        boolean flag = adminService.saveOrUpdate(admin);
        if (flag) {
            return Result.data(admin, "修改成功");
        }
        return Result.fail();
    }


    @PostMapping("/admins")
    public Result<SysAdmin> admin(@RequestBody SysAdmin admin) {
        admin.setStatus(false);
        admin.setRid(2L);
        admin.setPassword(MD5Utils.code(admin.getPassword()));
        admin.setToken(TokenUtils.getToken(adminService.count() + 1L));
        boolean flag = adminService.save(admin);
        if (flag) {
            return Result.data(admin, "用户保存成功");
        }
        return Result.fail();
    }

    @GetMapping("/users/{id}")
    public Result<SysAdmin> users(@PathVariable("id") Long id) {
        return Result.data(adminService.getById(id));
    }

    @GetMapping("/users/{id}/{realName}")
    public Result<SysAdmin> users(@PathVariable("id") Long id, @PathVariable("realName") String realName) {
        boolean flag = adminService.lambdaUpdate().eq(SysAdmin::getId, id).set(SysAdmin::getRealName, realName).update();
        if (flag) {
            return Result.success("登记人修改成功");
        }
        return Result.fail();
    }

    @DeleteMapping("/users/{id}")
    public Result<SysAdmin> deleteUser(@PathVariable("id") Long id) {
        boolean flag = adminService.removeById(id);
        if (flag) {
            return Result.success("删除成功");
        }
        return Result.fail();
    }

    @GetMapping("/roles/{id}/{rid}")
    public Result<SysAdmin> roles(@PathVariable("id") Long id, @PathVariable("rid") Long rid) {
        boolean flag = adminService.lambdaUpdate().eq(SysAdmin::getId, id).set(SysAdmin::getRid, rid).update();
        if (flag) {
            return Result.success("修改用户角色");
        }
        return Result.fail();
    }
}
