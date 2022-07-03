package com.slcp.devops;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: Slcp
 * @date: 2020/9/22 11:26
 * @code: 一生的挚爱
 * @description: 自定义异常
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFountException extends RuntimeException{
    public NotFountException() {
        super();
    }

    public NotFountException(String message) {
        super(message);
    }

    public NotFountException(String message, Throwable cause) {
        super(message, cause);
    }
}
