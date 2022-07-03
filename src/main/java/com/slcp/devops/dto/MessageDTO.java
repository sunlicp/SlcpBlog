package com.slcp.devops.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 12:50
 * @code: 一生的挚爱
 * @description: 留言实体类
 */
@Data
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = -1309782578272943999L;

    private String messageId;
    private String email;
    private String nickname;
    private String content;
    private String avatar;
    private Date createTime;
    private String parentMessageId;
    private Boolean adminMessage;

    private List<MessageDTO> replyMessages;
    private MessageDTO parentMessage;
    private String parentNickname;

}
