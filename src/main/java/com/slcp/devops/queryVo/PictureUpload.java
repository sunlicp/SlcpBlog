package com.slcp.devops.queryVo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author: Slcp
 * @date: 2020/11/8 19:05
 * @code: 一生的挚爱
 * @description:
 */
public class PictureUpload{
    private Long id;
    private String picturename;
    private String picturetime;
    private String picturedescription;
    private String picturepath;
    private MultipartFile pictureupload;

    @Override
    public String toString() {
        return "PictureUpload{" +
                "id=" + id +
                ", picturename='" + picturename + '\'' +
                ", picturetime='" + picturetime + '\'' +
                ", picturedescription='" + picturedescription + '\'' +
                ", picturepath='" + picturepath + '\'' +
                ", pictureupload=" + pictureupload +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicturename() {
        return picturename;
    }

    public void setPicturename(String picturename) {
        this.picturename = picturename;
    }

    public String getPicturetime() {
        return picturetime;
    }

    public void setPicturetime(String picturetime) {
        this.picturetime = picturetime;
    }

    public String getPicturedescription() {
        return picturedescription;
    }

    public void setPicturedescription(String picturedescription) {
        this.picturedescription = picturedescription;
    }

    public String getPicturepath() {
        return picturepath;
    }

    public void setPicturepath(String picturepath) {
        this.picturepath = picturepath;
    }

    public MultipartFile getPictureupload() {
        return pictureupload;
    }

    public void setPictureupload(MultipartFile pictureupload) {
        this.pictureupload = pictureupload;
    }

    public PictureUpload(Long id, String picturename, String picturetime, String picturedescription, String picturepath, MultipartFile pictureupload) {
        this.id = id;
        this.picturename = picturename;
        this.picturetime = picturetime;
        this.picturedescription = picturedescription;
        this.picturepath = picturepath;
        this.pictureupload = pictureupload;
    }

    public PictureUpload() {
    }
}
