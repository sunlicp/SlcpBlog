package com.slcp.devops.service;

import com.slcp.devops.pojo.Picture;
import com.slcp.devops.queryVo.PictureUpload;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/23 20:01
 * @code: 一生的挚爱
 * @description:
 */
public interface PictureService {
    List<Picture> listPictures();

    int savePicture(Picture picture);

    Picture getPictureById(Long id);

    int updatePicture(Picture picture);

    void deletePicture(Long id);


    int savePictureUpload(PictureUpload pictureUpload);

    List<PictureUpload> listUpload();

    PictureUpload getUploadById(Long id);

    int uploadDelete(Long id);

    int updatePictureUpload(PictureUpload fileUpload);

}
