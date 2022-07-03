package com.slcp.devops.constant;

import lombok.experimental.UtilityClass;

/**
 * DevOps基本常量
 *
 * @author devops
 */
@UtilityClass
public class DevOpsConstant {
    /**
     * 默认为空消息
     */
    public static final String DEFAULT_NULL_MESSAGE = "承载数据为空";
    /**
     * 默认成功消息
     */
    public static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    /**
     * 默认失败消息
     */
    public static final String DEFAULT_FAIL_MESSAGE = "处理失败";
    /**
     * 树的根节点值
     */
    public static final Long TREE_ROOT = -1L;
    /**
     * 允许的文件类型，可根据需求添加
     */
    public static final String[] VALID_FILE_TYPE = {"xlsx", "zip","pdf"};

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String METHOD_POST = "POST";

    public static final String METHOD_GET = "GET";

}
