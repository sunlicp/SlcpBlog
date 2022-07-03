package com.slcp.devops.exception;

import com.slcp.devops.enums.FlowableExceptionEnum;
import com.slcp.devops.api.ResultCode;
import com.slcp.devops.enums.ExceptionBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础异常
 *
 * @author ruoyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class DevopsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DevopsException(FlowableExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.defaultMessage = exceptionEnum.getMessage();
    }

    public DevopsException(FlowableExceptionEnum exceptionEnum, String defaultMessage) {
        this.code = exceptionEnum.getCode();
        this.defaultMessage = defaultMessage;
    }

    public DevopsException(ExceptionBaseEnum baseEnum) {
        this.code = baseEnum.getCode();
        this.defaultMessage = baseEnum.getMessage();
    }

    public DevopsException(ResultCode resultCode, String details) {
        this.code = resultCode.getCode();
        this.defaultMessage = details;
        this.details = details;
    }


    /**
     * 错误码
     */
    private int code;

    /**
     * 错误消息
     */
    private String defaultMessage;

    /**
     * 消息详情描述
     */
    private String details;
}
