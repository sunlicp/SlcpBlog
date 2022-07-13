package com.slcp.devops.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回码实现
 *
 * @author devops
 */

@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 业务异常
     */
    FAILURE(400, "业务异常"),
    /**
     * 服务未找到
     */
    NOT_FOUND(404, "服务未找到"),
    /**
     * 服务异常
     */
    ERROR(500, "服务异常"),
    /**
     * 请授权后操作
     */
    LIMITED_OPS(423, "受限操作，请授权后操作"),
    /**
     * 请完成实名认证
     */
    UN_CERTIFICATION(424, "请完成实名认证"),
    /**
     * Too Many Requests
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests");

    /**
     * 状态码
     */
    final int code;
    /**
     * 消息内容
     */
    final String msg;
}
