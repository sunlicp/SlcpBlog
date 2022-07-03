package com.slcp.devops.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author: Slcp
 * @date: 2020/9/22 12:54
 * @code: 一生的挚爱
 * @description: 照片墙实体类
 */
@Data
public class Picture {
    private Integer pictureId;
    private String picturename;
    private String picturetime;
    private String pictureaddress;
    private String picturedescription;
}
