package com.slcp.devops.zhifubaopay;

import com.alipay.api.AlipayApiException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单接口
 * @author Slcp
 */
@Controller()
@RequestMapping("/order")
public class OrderController {
    @Resource
    private PayService payService;

    /**
     * 阿里支付
     * @return 数据
     */
    @GetMapping("/pay")
    public String pay() {
        return "pay";
    }


    /**
     * 阿里支付
     * @param outTradeNo 商户订单号
     * @param subject 订单名称
     * @param totalAmount 付款金额
     * @param body 商品描述
     * @return String
     * @throws AlipayApiException a
     */
    @PostMapping(value = "/alipay")
    @ResponseBody
    public String alipay(String outTradeNo, String subject, String totalAmount, String body) throws AlipayApiException {

        return payService.aliPay(new AlipayBean()
                .setBody(body)
                .setOutTradeNo(outTradeNo)
                .setTotalAmount(new StringBuffer().append(totalAmount))
                .setSubject(subject));
    }
}