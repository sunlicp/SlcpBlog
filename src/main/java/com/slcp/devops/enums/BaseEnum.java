package com.slcp.devops.enums;

import java.io.Serializable;

/**
 * @author slcp
 * @date 2021/7/30 19:34
 * @description
 */
public interface BaseEnum extends Serializable {
    /**
     * 获取枚举值
     * @return 枚举值
     */
    String getValue();

    /**
     * 获取枚举名称
     * @return 枚举名称
     */
    String getDesc();
}
