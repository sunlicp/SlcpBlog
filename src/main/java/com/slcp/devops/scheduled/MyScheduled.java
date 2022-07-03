package com.slcp.devops.scheduled;

import com.slcp.devops.utils.ColorUtil;
import com.slcp.devops.utils.LoveEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.slcp.devops.utils.LoveEmail.getOneS;

/**
 * @author: Slcp
 * @date: 2021/1/17 20:52
 * @code: 一生的挚爱
 * @description:
 */
@Controller
@AllArgsConstructor
public class MyScheduled {

    private final LoveEmail LoveEmail;

    private static String sendCount_Key = "sendCount_Key";
    private static Long sendCountValue = 1L;


    /**
     * cronExpression定义时间规则：秒 分钟 小时 日期 月份 星期 年（可选）
     * 定时执行任务方法 每天5点和17点20执行该任务
     * @Scheduled(cron = "0 20 5 * * *")
     */
    @RequestMapping("/love")
    public void dsrw() {
        try {
            //获取彩虹屁
            String message = getOneS();
            //获取邮件模板
            String content = getContent(message);
            //发送邮件
//            if (redisTemplate.opsForValue().get(sendCount_Key) != null) {  //计数器是否存在
//                sendCountValue = Long.valueOf(redisTemplate.opsForValue().get(sendCount_Key));
//            }
//            LoveEmail.sendMessage("❤老婆", content);
            LoveEmail.sendMessage("❤小公主", content);
            /*boolean flag = LoveEmail.sendMessage("❤老婆", content);
            if (flag) {
                LoveEmail.sendMessage("❤老婆", content);
//                System.out.println("--------发送第"+sendCountValue+"封邮件成功--------");
            }*/
//            sendCountValue = redisTemplate.opsForValue().increment(sendCount_Key); //自增
//            redisTemplate.opsForValue().set(sendCount_Key, String.valueOf(sendCountValue)); //存入redis
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return "index";
    }


    public String getContent(String message) {
        //格式化日期
        String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        String color = ColorUtil.getRandColor();
        //自定义样式邮件模板
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <base target=\"_blank\"/>\n" +
                "<link href=\"https://fonts.googleapis.com/css2?family=Noto+Serif+SC&amp;family=Zhi+Mang+Xing&amp;family=Luckiest+Guy&amp;display=swap\" rel=\"stylesheet\">"+
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
                "    }\n" +
                "\n" +
                "#subtitle {\n" +
                "\tanimation: change 5s linear  0s infinite;\n" +
                "}\n" +
                "\n" +
                "#subtitle {" +
                "font-family: 'Zhi Mang Xing', cursive;" +
                "}\n" +
                "\n" +
                "@keyframes change {\n" +
                "\n" +
                "0% {color: "+color+";}\n" +
                "\n" +
                "50% {color: "+ColorUtil.getRandColor()+";}\n" +
                "\n" +
                "100% {color: "+color+";}\n" +
                "\n" +
                "}"+
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"width: 530px;margin: 20px auto 0;height: 1000px;\">\n" +
                "    <div id=\"form-wrap\"><img src=\"https://cdn.jsdelivr.net/gh/Akilarlxh/Valine-Admin@v1.0/source/img/before.png\"\n" +
                "                             alt=\"before\"\n" +
                "                             style=\"position: absolute;bottom: 126px;left: 0px;background-repeat: no-repeat;width: 530px;height: 317px;z-index:-100\">\n" +
                "        <div style=\"position: relative;overflow: visible;height: 1500px;width: 500px;margin: 0px auto;transition: all 1s ease-in-out .3s;padding-top:200px;\">\n" +
                "            <form>\n" +
                "                <div style=\"background: white;width: 95%;max-width: 800px;margin: auto auto;border-radius: 5px;border: 1px solid;overflow: hidden;-webkit-box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.12);box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.18);\">\n" +
                "                    <img style=\"width:100%;overflow: hidden;\"\n" +
                "                         src=\"https://ae01.alicdn.com/kf/U5bb04af32be544c4b41206d9a42fcacfd.jpg\"/>\n" +
                "                    <div style=\"padding: 5px 20px;\"><br>\n" +
                "                        <div><h3\n" +
                "                                style=\"text-decoration: none; color: rgb(17,17,17); text-align: center;font-family: 华文新魏\">\n" +
                "                            来自你的<span style=\"color: "+ColorUtil.getRandColor()+"\">大护卫</span>的留言:</h3>\n" +
                "                        </div>\n" +
                "\n" +
                "                        <br>\n" +
                "                        <!--内容区域height:200px-->\n" +
                "                        <div id=\"letter\"\n" +
                "                             style=\"overflow:auto;height:285px;width:100%;display:block;word-break: break-all;word-wrap: break-word;\">\n" +
                "                            <div style=\"text-align: center; border-bottom: #ddd 1px solid;border-left: #ddd 1px solid;padding-bottom: 20px;background-color: #eee;margin: 15px 0px;padding-left: 20px;padding-right: 20px;border-top: #ddd 1px solid;border-right: #ddd 1px solid;padding-top: 20px;font-family: \"\n" +
                "                                 Arial\n" +
                "                            \", \"Microsoft YaHei\" , \"黑体\" , \"宋体\" , sans-serif;\">\n" +
                "                            <!--要显示的内容-->\n" +
                "                            <span id=\"subtitle\" style=\"color: "+color+";font-family: 华文新魏\">" +
                 message +  //"第" + sendCountValue + "份爱："
                "</span>\n" +
                "\n" +
                "                        </div>\n" +
                "\n" +
                "                        <div style=\"text-align: center;margin-top: 40px;\"><img\n" +
                "                                src=\"https://ae01.alicdn.com/kf/U0968ee80fd5c4f05a02bdda9709b041eE.png\" alt=\"hr\"\n" +
                "                                style=\"width:100%; margin:5px auto 5px auto; display: block;\"/><a\n" +
                "                                style=\"text-transform: uppercase;text-decoration: none;font-size: 17px;border: 2px solid #6c7575;color: #2f3333;padding: 10px;display: inline-block;margin: 10px auto 0;background-color: rgb(246, 214, 175);\"\n" +
                "                                target=\"_blank\" href=\"#\">" +
                date +
                "</a>\n" +
                "                        </div>\n" +
                "                        <p style=\"font-size: 12px;text-align: center;color: #999;\">(๑′ᴗ‵๑)Ｉ Lᵒᵛᵉᵧₒᵤ❤<br><a\n" +
                "                                style=\"text-decoration:none; color:rgb(30,171,234)\" href=\"http://www.Slcp.top\">@\n" +
                "                            Slcp</a>\n" +
                "                        </p>\n" +
                "\n" +
                "                    </div>\n" +
                "\n" +
                "                </div>\n" +
                "        </div>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "    <img src=\"https://cdn.jsdelivr.net/gh/Akilarlxh/Valine-Admin@v1.0/source/img/after.png\" alt=\"after\"\n" +
                "         style=\"      position: absolute;bottom: -2px;left: 0;background-repeat: no-repeat;width: 530px;height: 259px;z-index:100\">\n" +
                "</div>\n" +
                "</div>\n" +
                "</body>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        return content;
    }
}
