package com.slcp.devops.zhifubaopay;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 支付实体对象
 * @author Slcp
 */
@Data
@Accessors(chain = true)
public class AlipayBean {
 /**
  * 商户订单号，必填
  */
 private String outTradeNo;
 /**
  * 订单名称，必填
  */
 private String subject;
 /**
  * 付款金额，必填
  */
 private StringBuffer totalAmount;
 /**
  * 商品描述，可空
  */
 private String body;
 /**
  * 超时时间参数
  */
 private String timeoutExpress ="10m";
 private String productCode ="FAST_INSTANT_TRADE_PAY";
}