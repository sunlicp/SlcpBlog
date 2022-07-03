package com.slcp.devops.utils;

import com.slcp.devops.entity.RegisterUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author: Slcp
 * @date: 2020/10/29 17:30
 * @code: 一生的挚爱
 * @description:
 */
@Component
public class EmailUtils {
    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 简单邮件
     * @param email 邮箱
     * @return r
     */
    public boolean sendMessage(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Slcp");
        message.setText("简单的邮件发送");

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.send(message);

        return true;
    }

    /**
     * 复杂邮件发送(留言回复专用)
     * @param nickname 昵称
     * @param content 内容
     * @param reMessageContent 留言内容
     * @param email 邮箱
     * @throws MessagingException 异常
     */
    public void sendReMessage(String nickname,String content,String reMessageContent,String email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(email);
        helper.setSubject("Slcp小栈");
        String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        helper.setText("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<head>\n" +
                "    <base target=\"_blank\"/>\n" +
                "    <style id=\"scrollbar\" type=\"text/css\">::-webkit-scrollbar {\n" +
                "        width: 0 !important\n" +
                "    }\n" +
                "\n" +
                "    pre {\n" +
                "        white-space: pre-wrap !important;\n" +
                "        word-wrap: break-word !important;\n" +
                "        *white-space: normal !important\n" +
                "    }\n" +
                "\n" +
                "    pre {\n" +
                "        white-space: pre-wrap !important;\n" +
                "        word-wrap: break-word !important;\n" +
                "        *white-space: normal !important\n" +
                "    }\n" +
                "\n" +
                "    #letter img {\n" +
                "        max-width: 300px\n" +
                "    }</style>\n" +
                "    <style id=\"from-wrapstyle\" type=\"text/css\">#form-wrap {\n" +
                "        overflow: hidden;\n" +
                "        height: 447px;\n" +
                "        position: relative;\n" +
                "        top: 0px;\n" +
                "        transition: all 1s ease-in-out .3s;\n" +
                "        z-index: 0\n" +
                "    }</style>\n" +
                "    <style id=\"from-wraphoverstyle\" type=\"text/css\">#form-wrap:hover {\n" +
                "        height: 1300px;\n" +
                "        top: -200px\n" +
                "    }</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"width: 530px;margin: 20px auto 0;height: 1000px;\">\n" +
                "    <div id=\"form-wrap\"><img src=\"https://cdn.jsdelivr.net/gh/Akilarlxh/Valine-Admin@v1.0/source/img/before.png\"\n" +
                "                             alt=\"before\"\n" +
                "                             style=\"position: absolute;bottom: 126px;left: 0px;background-repeat: no-repeat;width: 530px;height: 317px;z-index:-100\">\n" +
                "        <div style=\"position: relative;overflow: visible;height: 1500px;width: 500px;margin: 0px auto;transition: all 1s ease-in-out .3s;padding-top:200px;\"\n" +
                "        <form>\n" +
                "            <div style=\"background: white;width: 95%;max-width: 800px;margin: auto auto;border-radius: 5px;border: 1px solid;overflow: hidden;-webkit-box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.12);box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.18);\">\n" +
                "                <img style=\"width:100%;overflow: hidden;\"\n" +
                "                     src=\"https://ae01.alicdn.com/kf/U5bb04af32be544c4b41206d9a42fcacfd.jpg\"/>\n" +
                "                <div style=\"padding: 5px 20px;\"><br>\n" +
                "                    <div><h3 style=\"text-decoration: none; color: rgb(246, 214, 175);\">\n" +
                nickname
                +
                " ，见信安：</h3></div>\n" +
                "                    <div id=\"letter\"\n" +
                "                         style=\"overflow:auto;height:300px;width:100%;display:block;word-break: break-all;word-wrap: break-word;\">\n" +
                "                        <p style=\"display: inline-block;\">您在<a style=\"text-decoration: none;color: rgb(246, 214, 175)\"\n" +
                "                                                               target=\"_blank\"\n" +
                "                                                               href=\"#\">\n" +
                "                            Slcp小栈</a>上发表的评论:\n" +
                "                        </p>\n" +
                "                        <div align=\"center\">❤</div>\n" +
                "\n" +
                "                        <div style=\"border-bottom: #ddd 1px solid;border-left: #ddd 1px solid;padding-bottom: 20px;background-color: #eee;margin: 10px 0px;padding-left: 20px;padding-right: 20px;border-top: #ddd 1px solid;border-right: #ddd 1px solid;padding-top: 20px;font-family: \"\n" +
                "                             Arial\n" +
                "                        \", \"Microsoft YaHei\" , \"黑体\" , \"宋体\" , sans-serif;\">\n" +

                content
                +
                "\n" +
                "                    </div>\n" +
                "                    <p>收到了来自<span style=\"color:rgb(246, 214, 175)\">Slcp</span>的回复：</p>\n" +
                "                    <div style=\"border-bottom: #ddd 1px solid;border-left: #ddd 1px solid;padding-bottom: 20px;background-color: #eee;margin: 15px 0px;padding-left: 20px;padding-right: 20px;border-top: #ddd 1px solid;border-right: #ddd 1px solid;padding-top: 20px;font-family: \"\n" +
                "                         Arial\n" +
                "                    \", \"Microsoft YaHei\" , \"黑体\" , \"宋体\" , sans-serif;\">\n" +
                reMessageContent
                +
                "                </div>\n" +
                "                <!--放入滑动的div中，尾部显示则整体放下去一个div-->\n" +
                "                <div style=\"text-align: center;margin-top: 40px;\"><img\n" +
                "                        src=\"https://ae01.alicdn.com/kf/U0968ee80fd5c4f05a02bdda9709b041eE.png\" alt=\"hr\"\n" +
                "                        style=\"width:100%; margin:5px auto 5px auto; display: block;\"/><a\n" +
                "                        style=\"text-transform: uppercase;text-decoration: none;font-size: 17px;border: 2px solid #6c7575;color: #2f3333;padding: 10px;display: inline-block;margin: 10px auto 0;background-color: rgb(246, 214, 175);\"\n" +
                "                        target=\"_blank\" href=\"#\">" +
                date
                +
                "</a>\n" +
                "                </div>\n" +
                "                <p style=\"font-size: 12px;text-align: center;color: #999;\">你若盛开,Slcp自来！<br><a\n" +
                "                        style=\"text-decoration:none; color:rgb(30,171,234)\" href=\"http://www.qingfenginn.top\">\n" +
                "                    @ Slcp小栈</a>\n" +
                "                </p>\n" +
                "\n" +
                "            </div>\n" +
                "\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<!--</form>-->\n" +
                "</div>\n" +
                "<img src=\"https://cdn.jsdelivr.net/gh/Akilarlxh/Valine-Admin@v1.0/source/img/after.png\" alt=\"after\"\n" +
                "     style=\"      position: absolute;bottom: -2px;left: 0;background-repeat: no-repeat;width: 530px;height: 259px;z-index:100\">\n" +
                "</div>\n" +
                "</div>\n" +
                "</body>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>",true);
        javaMailSender.send(message);
    }

    /**
     * 复杂邮件发送(注册用户专用)
     * @param registerUser 用户
     * @throws MessagingException 异常
     */
    public void sendMimeMessage(RegisterUser registerUser) throws MessagingException {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(registerUser.getEmail());

            helper.setSubject("Slcp小栈");

            //邮件内容时间格式化
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();

            //开启html样式
            helper.setText("<!doctype html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>注册成功</title>\n" +
                    "    <meta name=\"keywords\" content=\"注册成功\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge, chrome=1\">\n" +
                    "\n" +
                    "</head>\n" +
                    "<body  style=\"background-color:#ECECEC; padding: 35px;\">\n" +
                    "<div>\n" +
                    "    <table cellpadding=\"0\" align=\"center\"\n" +
                    "           style=\"width: 600px; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
                    "        <tbody>\n" +
                    "        <tr>\n" +
                    "            <th valign=\"middle\"\n" +
                    "                style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #42a3d3; background-color: #49bcff; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
                    "                <font face=\"微软雅黑\" size=\"5\" style=\"color: rgb(255, 255, 255); \">注册成功! （Slcp小栈）</font>\n" +
                    "            </th>\n" +
                    "        </tr>\n" +
                    "        <tr>\n" +
                    "            <td>\n" +
                    "                <div style=\"padding:25px 35px 40px; background-color:#fff;\">\n" +
                    "                    <h2 style=\"margin: 5px 0px; \">\n" +
                    "                        <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
                    "                            <font style=\"line-height: 22px; \" size=\"4\">\n" +
                    "                                亲爱的 <span>" +
                    registerUser.getUsername() +
                    "</span></font>\n" +
                    "                        </font>\n" +
                    "                    </h2>\n" +
                    "                    <p>首先感谢您加入Slcp小栈！下面是您的账号信息<br>\n" +
                    "<!--                        您的账号：<b><span>" +
                    registerUser.getUsername() +
                    "</span></b><br>-->\n" +
                    "                        您的密码：<b><span>" +
                    registerUser.getPassword() +
                    "</span></b><br>\n" +
                    "                        您注册时的日期：<b>" +
                    simpleDateFormat.format(date) +
                    "</b><br>\n" +
                    "                        您的邮箱：<a><b><span>" +
                    registerUser.getEmail() +
                    "</span></b></a><br>\n" +
                    "                        当您在使用本网站时，遵守当地法律法规。<br>\n" +
                    "                        如果您有什么疑问可以联系管理员，Email: 1305366530@qq.com</p>\n" +
                    "                    <p align=\"right\"><b>" +
                    "</b></p>\n" +
                    "                    <p align=\"right\">" +
                    simpleDateFormat.format(date) +
                    "</p>\n" +
                    "                    <div style=\"width:700px;margin:0 auto;\">\n" +
                    "                        <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                    "                            <p>此为系统邮件，请勿回复<br>\n" +
                    "                                请保管好您的邮箱，避免账号被他人盗用\n" +
                    "                            </p>\n" +
                    "                            <p>©Slcp</p>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "        </tbody>\n" +
                    "    </table>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "\n" +
                    "\n" +
                    "\n", true);
            //发送附件
//        helper.addAttachment("1.jpg",new File("C:\\Users\\ASUS\\Desktop\\1.jpg"));
            javaMailSender.send(message);

    }


    /**
     * 忘记密码，(找回密码专用)
     * @param username 账号
     * @param email 邮箱
     * @param decodePassword 密码
     * @throws MessagingException 异常
     */
    public void sendMimeMessageRetrievePassword(String username, String email, String decodePassword) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(email);

        helper.setSubject("Slcp小栈");

        //邮件内容时间格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        //开启html样式
        helper.setText("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>密码找回成功</title>\n" +
                "    <meta name=\"keywords\" content=\"密码找回成功\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge, chrome=1\">\n" +
                "\n" +
                "</head>\n" +
                "<body  style=\"background-color:#ECECEC; padding: 35px;\">\n" +
                "<div>\n" +
                "    <table cellpadding=\"0\" align=\"center\"\n" +
                "           style=\"width: 600px; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <th valign=\"middle\"\n" +
                "                style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #42a3d3; background-color: #49bcff; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
                "                <font face=\"微软雅黑\" size=\"5\" style=\"color: rgb(255, 255, 255); \">密码找回成功! （Slcp小栈）</font>\n" +
                "            </th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <div style=\"padding:25px 35px 40px; background-color:#fff;\">\n" +
                "                    <h2 style=\"margin: 5px 0px; \">\n" +
                "                        <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
                "                            <font style=\"line-height: 22px; \" size=\"4\">\n" +
                "                                亲爱的 <span>" +
                username
                +
                "</span></font>\n" +
                "                        </font>\n" +
                "                    </h2>\n" +
                "                    <p>首先感谢您再次光临Slcp小栈！下面是您的账号信息<br>\n" +
                "                        您的账号是：<a><b><span>" +
                username +
                "</span></b></a><br>\n" +
                "                        您的密码是：<b><span>" +
                decodePassword +
                "</span></b><br>\n" +
                "                        您的邮箱是：<a><b><span>" +
                email +
                "</span></b></a><br>\n" +
                "                        当您在使用本网站时，遵守当地法律法规。<br>\n" +
                "                        如果您有什么疑问可以联系管理员，Email: 1305366530@qq.com</p>\n" +
                "                    <p align=\"right\"><b>" +
                "</b></p>\n" +
                "                    <p align=\"right\">" +
                simpleDateFormat.format(date) +
                "</p>\n" +
                "                    <div style=\"width:700px;margin:0 auto;\">\n" +
                "                        <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                "                            <p>此为系统邮件，请勿回复<br>\n" +
                "                                请保管好您的邮箱，避免账号被他人盗用\n" +
                "                            </p>\n" +
                "                            <p>©Slcp</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "</div>\n" +
                "</body>\n" +
                "\n" +
                "\n" +
                "\n", true);
        //发送附件
//        helper.addAttachment("1.jpg",new File("C:\\Users\\ASUS\\Desktop\\1.jpg"));
        javaMailSender.send(message);
    }
}
