package com.slcp.devops.mapper;

import com.slcp.devops.pojo.FriendLink;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/23 10:20
 * @code: 一生的挚爱
 * @description:
 */
@Repository
public interface FriendLinkMapper {
    List<FriendLink> listFriendLike();

    FriendLink getFriendLink(String blogaddress);

    int saveFriendLink(FriendLink friendLink);

    FriendLink getFriendLinkById(Integer id);

    int updateFriendLink(FriendLink friendLink);

    void deleteFriendLink(Long id);
}
