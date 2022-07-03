package com.slcp.devops.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 12:50
 * @code: 一生的挚爱
 * @description: 留言实体类
 */
@Data
public class Message {
    private String messageId;
    private String email;
    private String nickname;
    private String content;
    private String avatar;
    private Date createTime;
    private String parentMessageId;
    private boolean adminMessage;

    private List<Message> replyMessages = new ArrayList<>();
    private Message parentMessage;
    private String parentNickname;

}
