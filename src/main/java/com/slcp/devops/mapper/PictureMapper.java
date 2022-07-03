package com.slcp.devops.mapper;

import com.slcp.devops.pojo.Picture;
import com.slcp.devops.queryVo.PictureUpload;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/23 20:01
 * @code: 一生的挚爱
 * @description:
 */
@Repository
public interface PictureMapper {

    List<Picture> listPictures();

    int savePicture(Picture picture);

    Picture getPictureById(Long id);

    int updatePicture(Picture picture);

    void deletePicture(Long id);

    //上传新增
    int savePictureUpload(PictureUpload pictureUpload);

    List<PictureUpload> listUpload();

    PictureUpload getUploadById(Long id);

    int uploadDelete(Long id);

    int updatePictureUpload(PictureUpload fileUpload);

}
