package com.slcp.devops.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.slcp.devops.constant.DevOpsConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 统一响应消息报文
 *
 * @param <T> 　T对象
 * @author devops
 */
@Data
@Getter
@ApiModel(value = "统一响应消息报文")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码", required = true)
    private int code;

    @ApiModelProperty(value = "消息内容", required = true)
    private String msg;

    @ApiModelProperty(value = "时间戳", required = true)
    private long time;

    @ApiModelProperty(value = "业务数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    /**
     * 收发socket时用
     */
    private String messageEventType;
    /**
     * 消息通道，socket用
     */
    private String channel;


    private Result() {
        this.time = System.currentTimeMillis();
    }

    private Result(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMsg());
    }

    private Result(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private Result(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMsg());
    }

    private Result(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private Result(int code, T data, String msg) {

        this.code = code;
        this.data = data;
        this.msg = msg;
        this.time = System.currentTimeMillis();
    }

    public boolean isSuccess() {
        return code == ResultCode.SUCCESS.code;
    }

    /**
     * 返回状态码
     *
     * @param resultCode 状态码
     * @param <T>        泛型标识
     * @return ApiResult
     */
    public static <T> Result<T> success(IResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(ResultCode.SUCCESS, msg);
    }

    public static <T> Result<T> success(IResultCode resultCode, String msg) {
        return new Result<>(resultCode, msg);
    }

    public static <T> Result<T> data(T data) {
        return data(data, DevOpsConstant.DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> data(T data, String msg) {
        return data(ResultCode.SUCCESS.code, data, msg);
    }

    public static <T> Result<T> data(int code, T data, String msg) {
        return new Result<>(code, data, data == null ? DevOpsConstant.DEFAULT_NULL_MESSAGE : msg);
    }

    public static <T> Result<T> dataForSocket(String messageEventType, String channel, T data) {
        Result<T> result = data(data, DevOpsConstant.DEFAULT_SUCCESS_MESSAGE);
        result.setChannel(channel);
        result.setMessageEventType(messageEventType);
        return result;
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResultCode.FAILURE, ResultCode.FAILURE.getMsg());
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(ResultCode.FAILURE, msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, null, msg);
    }

    public static <T> Result<T> fail(IResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> fail(IResultCode resultCode, String msg) {
        return new Result<>(resultCode, msg);
    }

    public static <T> Result<T> condition(boolean flag) {
        return flag ? success(DevOpsConstant.DEFAULT_SUCCESS_MESSAGE) : fail(DevOpsConstant.DEFAULT_FAIL_MESSAGE);
    }

}
