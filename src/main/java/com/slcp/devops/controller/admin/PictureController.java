package com.slcp.devops.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.devops.api.Result;
import com.slcp.devops.constant.DevOpsConstant;
import com.slcp.devops.entity.Picture;
import com.slcp.devops.entity.Search;
import com.slcp.devops.entity.SqlWhereWrapper;
import com.slcp.devops.utils.QiniuUtils;
import com.slcp.devops.service.IPictureService;
import com.slcp.devops.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author: Slcp
 * @date: 2020/9/23 19:57
 * @code: 一生的挚爱
 * @description: 图片管理控制器
 */
@RestController
@RequestMapping("/sys")
@AllArgsConstructor
@Api(value = "图片管理接口", tags = "图片管理接口")
@Slf4j
public class PictureController {

    private final IPictureService pictureService;

    @GetMapping("/picture/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "getPictureById", notes = "根据主键获取信息")
    public Result<Picture> getPictureById(@PathVariable("id") Long id) {
        return Result.data(pictureService.getById(id));
    }

    @DeleteMapping("/picture/{id}")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_DELETE, value = "deletePicture", notes = "删除友链")
    public Result<Picture> deletePicture(@PathVariable("id") Long id) {
        StringUtil.delPicture(pictureService.getById(id).getPictureAddress());
        return Result.condition(pictureService.removeById(id));
    }

    @PostMapping("/picture")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "addPicture", notes = "添加&修改友链")
    public Result<Picture> addPicture(@RequestBody Picture picture) {
        return Result.condition(pictureService.saveOrUpdate(picture));
    }

    @GetMapping("/picture")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "pictureList", notes = "获取友链")
    public Result<IPage<Picture>> pictureList(@RequestParam Map<String, Object> queryParam, Search search) {
        IPage<Picture> listInfoByPage = SqlWhereWrapper.getPage(search);
        String pictureName = (String) queryParam.get("query");
        return Result.data(StringUtil.isBlank(pictureName) ? pictureService.page(listInfoByPage) : pictureService.page(listInfoByPage, new QueryWrapper<Picture>().lambda().like(Picture::getPictureName, pictureName)));
    }

    /**
     * 七牛云上传方式
     * 3.后端代码调用
     *
     * @param file 上传对象
     * @return 数据
     */
    @RequestMapping("/pictures/upload")
    public Result<Picture> upload(MultipartFile file) {
        if (ObjectUtil.isEmpty(file)) {
            return Result.fail("上传文件为空~");
        }
        //获取图片原始文件名
        String originalFilename = file.getOriginalFilename();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //进行拼接 时间+UUID+原始文件名+文件后缀名
        String fileName = format + "-" + UUID.randomUUID().toString().substring(0, 4) + "-" + originalFilename;
        try {
            //使用工具类将文件上传到七牛云服务器 返回上传图片链接
            String filePath = QiniuUtils.upload2Qiniu(file.getBytes(), fileName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("上传图片失败！" + e.getMessage());
        }
        return Result.fail("上传图片失败!");
    }

}
