package com.slcp.devops.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.slcp.devops.dto.UserDTO;
import com.slcp.devops.entity.SysAdmin;


/**
 * @Author: ✎﹏ Sunflower丶
 * @Specification: DELL
 */
public interface IAdminService extends IService<SysAdmin> {
    /**
     * 查询用户列表
     *
     * @param listInfoByPage     分页对象
     * @param query 请求参数
     * @return 用户列表
     */
    IPage<SysAdmin> listSysAdminPageOfXml(IPage<SysAdmin> listInfoByPage, String query);

    /**
     * 获取用户列表
     * @param listInfoByPage IPage
     * @param nickname 昵称
     * @return IPage
     */
    IPage<UserDTO> listUserPage(IPage<UserDTO> listInfoByPage, String nickname);

}
