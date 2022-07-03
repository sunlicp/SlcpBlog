package com.slcp.devops.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slcp.devops.dto.PictureUploadDTO;
import com.slcp.devops.entity.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/23 20:01
 * @code: 一生的挚爱
 * @description:
 */
@Repository
public interface IPictureMapper extends BaseMapper<Picture> {

    /**
     * 上传新增
     * @param pictureUpload p
     * @return r
     */
    int savePictureUpload(PictureUploadDTO pictureUpload);

    /**
     *  获取所有图片
     * @return r
     */
    List<PictureUploadDTO> listUpload();

    /**
     * 根据id查询
     * @param id 主键
     * @return r
     */
    PictureUploadDTO getUploadById(Long id);

    /**
     * 更新删除
     * @param id 主键
     * @return r
     */
    int uploadDelete(Long id);

    /**
     * 更新
     * @param fileUpload 文件
     * @return r
     */
    int updatePictureUpload(PictureUploadDTO fileUpload);

}
