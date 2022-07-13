package com.slcp.devops.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author: Slcp
 * @date: 2020/11/8 19:05
 * @code: 一生的挚爱
 * @description:
 */
@Data
public class PictureUploadDTO implements Serializable{
    private static final long serialVersionUID = -6809782578272943999L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String pictureName;
    private String pictureTime;
    private String pictureDescription;
    private String picturePath;
    private MultipartFile pictureUpload;
}
