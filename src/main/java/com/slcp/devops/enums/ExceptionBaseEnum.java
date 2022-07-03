package com.slcp.devops.enums;

/**
 * @author slcp
 * @date 2021/7/30 19:34
 * @description
 */
public interface ExceptionBaseEnum {
    /**
     * 获取枚举值
     * @return 枚举值
     */
    int getCode();

    /**
     * 获取枚举名称
     * @return 枚举名称
     */
    String getMessage();
}
