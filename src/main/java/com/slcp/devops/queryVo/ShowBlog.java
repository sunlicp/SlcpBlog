package com.slcp.devops.queryVo;

import com.slcp.devops.pojo.Tag;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:28
 * @code: 一生的挚爱
 * @description: 编辑修改文章实体类
 */
@Data
public class ShowBlog {
    private Long id;
    private String flag;
    private String title;
    private String content;
    private Long typeId;
    private String firstPicture; //url地址提交
    private MultipartFile pictureUpload; //或者文件上传方式提交

    private String description;
    private boolean recommend;
    private boolean published;
    private boolean shareStatement;
    private boolean appreciation;
    private boolean commentabled;
    private Date updateTime;

    private List<Tag> tags;
}
