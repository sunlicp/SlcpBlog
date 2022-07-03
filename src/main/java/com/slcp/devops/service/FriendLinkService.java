package com.slcp.devops.service;

import com.slcp.devops.pojo.FriendLink;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/23 10:20
 * @code: 一生的挚爱
 * @description:
 */
public interface FriendLinkService {
    List<FriendLink> listFriendLike();

    FriendLink getFriendLink(String blogaddress);


    int saveFriendLink(FriendLink friendLink);

    FriendLink getFriendLinkById(Integer id);

    int updateFriendLink(FriendLink friendLink);

    void deleteFriendLink(Long id);
}
