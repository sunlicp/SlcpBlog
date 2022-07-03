package com.slcp.devops.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.entity.FriendLink;
import com.slcp.devops.mapper.IFriendLinkMapper;
import com.slcp.devops.service.IFriendLinkService;
import org.springframework.stereotype.Service;

/**
 * @author: Slcp
 * @date: 2020/9/23 10:20
 * @code: 一生的挚爱
 * @description:
 */
@Service
public class FriendLinkServiceImpl extends ServiceImpl<IFriendLinkMapper, FriendLink> implements IFriendLinkService {

    @Override
    public void saveFriendLink(FriendLink friendLink) {
        //查看当前业务ID是否存在历史数据
        FriendLink one = this.getById(friendLink.getFriendId());
        if (ObjectUtil.isNotEmpty(one)) {
            friendLink.setFriendId(one.getFriendId());
        }
        this.saveOrUpdate(friendLink);
    }
}
