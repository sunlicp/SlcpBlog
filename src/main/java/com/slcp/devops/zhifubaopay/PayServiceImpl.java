package com.slcp.devops.zhifubaopay;

import com.alipay.api.AlipayApiException;
import org.springframework.stereotype.Service;

/**
 * 支付服务
 * @author Slcp
 */
@Service(value = "alipayOrderService")
public class PayServiceImpl implements PayService {
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return AlipayUtil.connect(alipayBean);
    }
}