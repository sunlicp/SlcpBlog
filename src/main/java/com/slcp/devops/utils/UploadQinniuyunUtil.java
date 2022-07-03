package com.slcp.devops.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author: Slcp
 * @date: 2021/8/27 13:58
 * @code: 一生的挚爱
 * @description:
 */
public class UploadQinniuyunUtil {

    public static String uploadQiniuyun(MultipartFile multipartFile) throws IOException {
        //上传图片
        if (!multipartFile.isEmpty()) {
            String originalFilename = multipartFile.getOriginalFilename();
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //进行拼接 时间+UUID+原始文件名+文件后缀名
            String fileName = format + "-" + UUID.randomUUID().toString().substring(0, 4) + "-" + originalFilename;
            //使用工具类将文件上传到七牛云服务器 返回上传图片链接
            return QiniuUtils.upload2Qiniu(multipartFile.getBytes(), fileName);
        }else {
            int v =  (int)(Math.random() * 100000);
            //一个随机图片接口
            String url="https://source.unsplash.com/random/"+v;
            byte[] bytes = ImgUtil.getImg(url);
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //进行拼接 时间+UUID+原始文件名+文件后缀名
            String fileName = format + "-" + UUID.randomUUID().toString().substring(0, 6) + "-" + v;
            //使用工具类将文件上传到七牛云服务器 返回上传图片链接
            return QiniuUtils.upload2Qiniu(bytes, fileName);
        }
    }
}
