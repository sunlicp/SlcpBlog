package com.slcp.devops.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Map;

/**
 * @author: Slcp
 * @date: 2020/10/29 19:54
 * @code: 一生的挚爱
 * @description:
 */
public class MessageUtils {

    public static boolean sendMessage(String phone, String template, Map code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G16F251gw8zRaePUPQo", "ZgYNq2hdFzpwWIotyYsJwphqtQDwfj");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        //自定义信息
        //发送至手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //自己配置的短信签名
        request.putQueryParameter("SignName", "Slcpの时光屋");
        //自己配置的模板 模版CODE
        request.putQueryParameter("TemplateCode", template);

        //构建一个短信验证码
        //转换成json字符串
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(code));
        boolean flag = false;
        try {
            //发送至客户端
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            //返回是否发送成功
            //标记
            flag = response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
