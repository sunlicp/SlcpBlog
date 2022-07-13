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
     * @param out_trade_no
     * @param subject
     * @param total_amount
     * @param body
     * @return
     * @throws AlipayApiException
     */
    @PostMapping(value = "/alipay")
    @ResponseBody
    public String alipay(String out_trade_no, String subject, String total_amount, String body) throws AlipayApiException {

        return payService.aliPay(new AlipayBean()
                .setBody(body)
                .setOut_trade_no(out_trade_no)
                .setTotal_amount(new StringBuffer().append(total_amount))
                .setSubject(subject));
    }
}