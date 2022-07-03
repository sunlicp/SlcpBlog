package com.slcp.devops.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slcp.devops.entity.FriendLink;

/**
 * @author: Slcp
 * @date: 2020/9/23 10:20
 * @code: 一生的挚爱
 * @description:
 */
public interface IFriendLinkService extends IService<FriendLink> {

    /**
     * 保存或修改
     * @param friendLink 友链对象
     */
    void saveFriendLink(FriendLink friendLink);
}
