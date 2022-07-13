package com.slcp.devops.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 13:28
 * @code: 一生的挚爱
 * @description: 编辑修改文章实体类
 */
@Data
public class ShowDTO implements Serializable {
    private static final long serialVersionUID = -1139782578272943999L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String flag;
    private String title;
    private String content;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;
    /**
     * url地址提交
     */
    private String firstPicture;
    /**
     * 或者文件上传方式提交
     */
    private MultipartFile pictureUpload;

    private String description;
    private Boolean recommend;
    private Boolean published;
    private Boolean shareStatement;
    private Boolean appreciation;
    private Boolean commentabled;
    private Date updateTime;

    private List<TagDTO> tags;
}
