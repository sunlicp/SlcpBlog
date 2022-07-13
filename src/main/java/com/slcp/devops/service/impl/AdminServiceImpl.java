package com.slcp.devops.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.dto.UserDTO;
import com.slcp.devops.entity.SysAdmin;
import com.slcp.devops.mapper.IAdminMapper;
import com.slcp.devops.service.IAdminService;
import org.springframework.stereotype.Service;


/**
 * @Author: ✎﹏ Sunflower丶
 * @Specification: DELL
 */
@Service
public class AdminServiceImpl extends ServiceImpl<IAdminMapper, SysAdmin> implements IAdminService {


    @Override
    public IPage<SysAdmin> listSysAdminPageOfXml(IPage<SysAdmin> listInfoByPage, String query) {
        return this.baseMapper.listSysAdminPageOfXml(listInfoByPage, query);
    }

    @Override
    public IPage<UserDTO> listUserPage(IPage<UserDTO> listInfoByPage, String nickname) {
        return this.baseMapper.listUserPage(listInfoByPage, nickname);
    }
}
