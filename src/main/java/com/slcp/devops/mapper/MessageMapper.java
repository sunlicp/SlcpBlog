package com.slcp.devops.mapper;

import com.slcp.devops.dto.MessageDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 14:53
 * @code: 一生的挚爱
 * @description:
 */
@Repository
public interface MessageMapper {
    /**
     * 添加一个评论
     * @param message 留言
     * @return 数值
     */
    int saveMessage(MessageDTO message);

    /**
     * 查询父级评论
     * @param parentId 父id
     * @return 数据
     */
    List<MessageDTO> findByParentIdNull(@Param("ParentId") Long parentId);

    /**
     * 查询一级回复
     * @param id 主键
     * @return 数据
     */
    List<MessageDTO> findByParentIdNotNull(@Param("id") String id);

    /**
     * 查询二级以及所有子集回复
     * @param childId 子id
     * @return 数据
     */
    List<MessageDTO> findByReplayId(@Param("childId") String childId);

    /**
     * 删除评论
     * @param id 主键
     */
    void deleteMessage(Long id);

    /**
     * 根据父留言id 查询留言信息
     * @param id 主键
     * @return 数据
     */
    MessageDTO getMessageByParentId(String id);

    /**
     * 获取消息
     * @return String
     */
    String getMessage();
}
