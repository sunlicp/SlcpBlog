package com.slcp.devops.zhifubaopay;

import com.alipay.api.AlipayApiException;

/**
 * 支付服务
 * @author Slcp
 */
public interface PayService {
    /**
     * 支付宝
     * @param alipayBean bean
     * @return 数据
     * @throws AlipayApiException
     */
    String aliPay(AlipayBean alipayBean) throws AlipayApiException;
}