package com.slcp.devops.service;

import com.slcp.devops.dto.MessageDTO;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 14:52
 * @code: 一生的挚爱
 * @description:
 */
public interface MessageService {
    /**
     * 留言查询
     * @return r
     */
    List<MessageDTO> listMessages();

    /**
     * 保存留言
     * @param message 留言
     */
    void saveMessage(MessageDTO message);

    /**
     * 删除
     * @param id 主键
     */
    void deleteMessage(Long id);

    /**
     * 获取父留言id，根据id信息获取名称，邮箱，内容
     * @param id 主键
     * @return 留言数据
     */
    MessageDTO getMessageByParentId(String id);

    /**
     * 获取信息
     * @return 信息
     */
    String getMessage();
}
