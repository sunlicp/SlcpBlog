package com.slcp.devops.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.dto.PictureUploadDTO;
import com.slcp.devops.entity.Picture;
import com.slcp.devops.mapper.IPictureMapper;
import com.slcp.devops.service.IPictureService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/23 20:01
 * @code: 一生的挚爱
 * @description:
 */
@Service
public class PictureServiceImpl extends ServiceImpl<IPictureMapper, Picture> implements IPictureService {

    @Override
    public int savePictureUpload(PictureUploadDTO pictureUpload) {
        return this.baseMapper.savePictureUpload(pictureUpload);
    }

    @Override
    public List<PictureUploadDTO> listUpload() {
        return this.baseMapper.listUpload();
    }

    @Override
    public PictureUploadDTO getUploadById(Long id) {
        return this.baseMapper.getUploadById(id);
    }

    @Override
    public int uploadDelete(Long id) {
        return this.baseMapper.uploadDelete(id);
    }

    @Override
    public int updatePictureUpload(PictureUploadDTO fileUpload) {
        return this.baseMapper.updatePictureUpload(fileUpload);
    }
}
