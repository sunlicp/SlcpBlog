package com.slcp.devops.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * @author: Slcp
 * @date: 2021/1/17 20:46
 * @code: 一生的挚爱
 * @description: 定时发送邮件模板
 */
@Component
public class LoveEmail {
    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Value("${she.mail}")
    private String[] sheMail;


    public boolean sendMessage(String subject, String message) {
        boolean flag = false;
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            //发送者邮件邮箱
            helper.setFrom(from);
            //收邮件者邮箱
            helper.setTo(sheMail);
            //发件主题
            helper.setSubject(subject);
            //发件内容
            helper.setText(message, true);
            //发送邮件
            javaMailSender.send(helper.getMimeMessage());
            flag = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean sendMessage(String subject, String message,String toMail) {
        boolean flag = false;
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            //发送者邮件邮箱
            helper.setFrom(from);
            //收邮件者邮箱
            helper.setTo(toMail);
            //发件主题
            helper.setSubject(subject);
            //发件内容
            helper.setText(message, true);
            //发送邮件
            javaMailSender.send(helper.getMimeMessage());
            flag=true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 远程获取要发送的信息
     */
    public static String getOneS() {
        try {
            //创建客户端对象
            HttpClient client = HttpClients.createDefault();
            /*创建地址 https://du.shadiao.app/api.php*/
            HttpGet get = new HttpGet("https://chp.shadiao.app/api.php");
            //发起请求，接收响应对象
            HttpResponse response = client.execute(get);
            //获取响应体，响应数据是一种基于HTTP协议标准字符串的对象
            //响应体和响应头，都是封装HTTP协议数据。直接使用可能出现乱码或解析错误
            HttpEntity entity = response.getEntity();
            //通过HTTP实体工具类，转换响应体数据

            return EntityUtils.toString(entity, "utf-8");

        } catch (IOException e) {
            throw new RuntimeException("网站获取句子失败");
        }
    }

    /**
     * 测试花去彩虹屁内容
     * @param args 参数
     * @throws IOException io
     */
    public static void main(String[] args) throws IOException {
        //创建客户端对象
        HttpClient client = HttpClients.createDefault();
        /*创建地址 https://du.shadiao.app/api.php*/
        HttpGet get = new HttpGet("https://chp.shadiao.app/api.php");
        //发起请求，接收响应对象
        HttpResponse response = client.execute(get);
        //获取响应体，响应数据是一种基于HTTP协议标准字符串的对象
        //响应体和响应头，都是封装HTTP协议数据。直接使用可能出现乱码或解析错误
        HttpEntity entity = response.getEntity();
        //通过HTTP实体工具类，转换响应体数据
        String responseString = EntityUtils.toString(entity, "utf-8");
        System.out.println(responseString);
    }
}
