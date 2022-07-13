package com.slcp.devops.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.api.Result;
import com.slcp.devops.config.DoQueryCache;
import com.slcp.devops.entity.FriendLink;
import com.slcp.devops.mapper.IFriendLinkMapper;
import com.slcp.devops.service.IFriendLinkService;
import com.slcp.devops.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
        FriendLink one = this.getById(friendLink.getId());
        if (ObjectUtil.isNotEmpty(one)) {
            friendLink.setId(one.getId());
        }
        this.saveOrUpdate(friendLink);
    }

    @Override
    public Result<?> saveOrUpdateByFriend(FriendLink friendLink) {
        String check = checkUrl(friendLink);
        if (StringUtil.isNotBlank(check)) {
            return Result.fail(check);
        }
        return Result.condition(this.saveOrUpdate(friendLink));
    }

    @Override
    @DoQueryCache
    public List<FriendLink> getFriends() {
        return this.lambdaQuery().eq(FriendLink::getStatus, 1).list();
    }

    /**
     * 校验
     *
     * @param friendLink 博客
     * @return String
     */
    private String checkUrl(FriendLink friendLink) {
        String blogAddress = friendLink.getBlogAddress();
        //基本校验
        if (StringUtil.isBlank(blogAddress)) {
            return "博客地址不能为空~";
        }
        if (StringUtil.isBlank(friendLink.getDescription())) {
            return "博客语录不能为空~";
        }
        if (StringUtil.isBlank(friendLink.getBlogName())) {
            return "博客名称不能为空~";
        }
        if (StringUtil.isBlank(friendLink.getHeadImg())) {
            return "博客头像不能为空~";
        }
        //第一次添加时触发
        if (ObjectUtil.isEmpty(friendLink.getId())) {
            //url校验
            URL url = null;
            FriendLink link;
            try {
                url = new URL(blogAddress);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if (ObjectUtil.isEmpty(url)) {
                link = this.lambdaQuery().eq(FriendLink::getBlogAddress, blogAddress).one();
            } else {
                link = this.lambdaQuery().like(FriendLink::getBlogAddress, url.getHost()).one();
            }

            if (ObjectUtil.isNotEmpty(link)) {
                return "不要捣蛋，汝博客已存在~";
            }
            friendLink.setStatus(false);
        }
        return null;
    }
}
