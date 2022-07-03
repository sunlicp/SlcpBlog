package com.slcp.devops.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slcp.devops.dto.PictureUploadDTO;
import com.slcp.devops.entity.Picture;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/23 20:01
 * @code: 一生的挚爱
 * @description:
 */
public interface IPictureService extends IService<Picture> {

    /**
     * 保存
     * @param pictureUpload 对象
     * @return 数值
     */
    int savePictureUpload(PictureUploadDTO pictureUpload);

    /**
     * 获取all
     * @return 数据
     */
    List<PictureUploadDTO> listUpload();

    /**
     * 获取
     * @param id 主键
     * @return r
     */
    PictureUploadDTO getUploadById(Long id);

    /**
     * 删除
     * @param id 主键
     * @return r
     */
    int uploadDelete(Long id);

    /**
     * 更新
     * @param fileUpload 对象
     * @return r
     */
    int updatePictureUpload(PictureUploadDTO fileUpload);

}
