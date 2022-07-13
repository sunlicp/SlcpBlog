package com.slcp.devops.controller;

import com.slcp.devops.entity.SysAdmin;
import com.slcp.devops.utils.EmailUtils;
import com.slcp.devops.dto.MessageDTO;
import com.slcp.devops.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 14:50
 * @code: 一生的挚爱
 * @description:
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private EmailUtils emailUtils;

    @Value("${message.avatar}")
    private String avatar;

    /**
     * 留言页面展示
     * @return 数据
     */
    @GetMapping("/message")
    public String message(Model model) {
        model.addAttribute("message", messageService.getMessage());
        return "message";
    }

    /**
     * 异步查询留言信息
     * @param model m
     * @return r
     */
    @GetMapping("/messagecomment")
    public String messages(Model model) {
        System.out.println("异步查询留言-----");
        List<MessageDTO> messages = messageService.listMessages();
        model.addAttribute("messages", messages);
        return "message :: messageList";
    }


    /**
     * 或取是否管理员或者用户登录过
     * @param session s
     * @return r
     */
    @GetMapping("/unUserOrRegisterUserSession")
    @ResponseBody
    public String unUserOrRegisterUserSession(HttpSession session) {
        Object unUserOrRegisterUserSession = session.getAttribute("user");
        Object registerUserSession = session.getAttribute("registerUser");
        if (unUserOrRegisterUserSession == null && registerUserSession == null) {
            return "0";
        } else {
            return "1";
        }

    }

    /**
     * 保存留言
     * @param message 留言
     * @param session s
     * @param model m
     * @return r
     */
    @PostMapping("/messages")
    public String post(MessageDTO message, HttpSession session, Model model) {
        //获取管理员信息
        SysAdmin user = (SysAdmin) session.getAttribute("user");
        //设置头像
        if (user != null) {
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        } else {
            message.setAvatar(avatar);
        }
        if (message.getParentMessage().getMessageId() != null) {
            message.setParentMessageId(message.getParentMessage().getMessageId());
            if (user != null) {
                //管理员邮件回复留言者(在有父留言的留言下发送邮件)
                MessageDTO reMessage = messageService.getMessageByParentId(message.getParentMessage().getMessageId());
                System.out.println("查询到对方的留言信息为: " + reMessage);
                if (reMessage!=null) {
                    String nickname = reMessage.getNickname();
                    String content = reMessage.getContent();
                    String email = reMessage.getEmail();
                    //2、发送邮件 回复内容给留言者
                    String reMessageContent = message.getContent();
                    try {
                        System.out.println("准备回复邮件给---: "+nickname);
                        emailUtils.sendReMessage(nickname, content, reMessageContent, email); //发给对方
                        emailUtils.sendReMessage(nickname, content, reMessageContent, "1305366530@qq.com"); //发给自己
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("准备保存留言---");
        messageService.saveMessage(message);
        List<MessageDTO> messages = messageService.listMessages();
        System.out.println("保存完毕查询留言---");

        //缓存留言数量+1

        model.addAttribute("messages", messages);
        return "message::messageList";
//        return "message";
    }

    @GetMapping("/messages/{id}/delete")
    public String deleteMessage(@PathVariable Long id, Model model) {
        messageService.deleteMessage(id);
        List<MessageDTO> messages = messageService.listMessages();

        //缓存留言数量-1

        model.addAttribute("messages", messages);
        return "redirect:/message";
    }

}
