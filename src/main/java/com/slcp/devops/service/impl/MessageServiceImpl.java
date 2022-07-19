package com.slcp.devops.service.impl;

import com.slcp.devops.config.DoQueryCache;
import com.slcp.devops.mapper.MessageMapper;
import com.slcp.devops.dto.MessageDTO;
import com.slcp.devops.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 14:52
 * @code: 一生的挚爱
 * @description:
 */
@Service
public class MessageServiceImpl implements MessageService {
    
    @Resource
    private MessageMapper messageMapper;

    /**
     * 存放迭代找出的所有子代的集合
     */
    private List<MessageDTO> tempReplys = new ArrayList<>();

    /**
     * 留言
     * @return 数据
     */
    @Override
    public List<MessageDTO> listMessages() {
        //查询出父节点
        List<MessageDTO> messages = messageMapper.findByParentIdNull(Long.parseLong("-1"));
        for(MessageDTO message : messages){
            String id = message.getMessageId();
            String parentNickname1 = message.getNickname();
            List<MessageDTO> childMessages = messageMapper.findByParentIdNotNull(id);
            //查询出子留言
            combineChildren(childMessages, parentNickname1);
            message.setReplyMessages(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return messages;
    }

    /**
     * 查询出子留言
     * @param childMessages 所有子留言
     * @param parentNickname1 父留言的姓名
     */
    private void combineChildren(List<MessageDTO> childMessages, String parentNickname1) {
        //判断是否有一级子回复
        if(childMessages.size() > 0){
            //循环找出子留言的id
            for(MessageDTO childMessage : childMessages){
                String parentNickname = childMessage.getNickname();
                childMessage.setParentNickname(parentNickname1);
                tempReplys.add(childMessage);
                String childId = childMessage.getMessageId();
                //查询二级以及所有子集回复
                recursively(childId, parentNickname);
            }
        }
    }

    /**
     * 循环迭代找出子集回复
     * @param childId 子留言的id
     * @param parentNickname1 子留言的姓名
     */
    private void recursively(String childId, String parentNickname1) {
        //根据子一级留言的id找到子二级留言
        List<MessageDTO> replayMessages = messageMapper.findByReplayId(childId);

        if(replayMessages.size() > 0){
            for(MessageDTO replayMessage : replayMessages){
                String parentNickname = replayMessage.getNickname();
                replayMessage.setParentNickname(parentNickname1);
                String replayId = replayMessage.getMessageId();
                tempReplys.add(replayMessage);
                //循环迭代找出子集回复
                recursively(replayId,parentNickname);
            }
        }
    }

    @Override
    public void saveMessage(MessageDTO message) {
        message.setCreateTime(new Date());
        messageMapper.saveMessage(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageMapper.deleteMessage(id);
    }

    @Override
    public MessageDTO getMessageByParentId(String id) {
        return messageMapper.getMessageByParentId(id);
    }

    @Override
    @DoQueryCache
    public String getMessage() {
        return messageMapper.getMessage();
    }

}
