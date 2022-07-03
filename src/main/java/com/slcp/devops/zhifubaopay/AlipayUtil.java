package com.slcp.devops.zhifubaopay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

/**
 * 支付宝
 * @author Slcp
 */
public class AlipayUtil {
    public static String connect(AlipayBean alipayBean) throws AlipayApiException {
        //1、获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                //支付宝网关
                PropertiesConfig.getKey("gatewayUrl"),
                //appid
                PropertiesConfig.getKey("app_id"),
                //商户私钥
                PropertiesConfig.getKey("merchant_private_key"),
                "json",
                //字符编码格式
                PropertiesConfig.getKey("charset"),
                //支付宝公钥
                PropertiesConfig.getKey("alipay_public_key"),
                //签名方式
                PropertiesConfig.getKey("sign_type")
        );
        //2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(PropertiesConfig.getKey("return_url"));
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(PropertiesConfig.getKey("notify_url"));
        //封装参数
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));

        //3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println(result);
        //返回付款信息
        return result;
    }
}