package com.slcp.devops.enums;

import lombok.Getter;

/**
 * 流程异常信息枚举
 *
 * @author slcp
 */
@Getter
public enum FlowableExceptionEnum {


    /**
     * 未找到对应的流程实例信息
     */
    INSTANCE_NOT_FOUND(6001, "未找到对应的流程实例信息"),
    /**
     * 未找到对应的流程实例信息
     */
    INSTANCE_ILLEGAL_ACCESS(6002, "流程实例已被禁止"),
    /**
     * 未找到对应的任务信息
     */
    TASK_NOT_FOUND(6101, "未找到对应的任务信息"),
    /**
     * 未找到对应的任务信息
     */
    TASK_NOT_ACCESS(6102, "您没有权限访问该任务"),

    /**
     * 任务参与者配置不可用
     */
    TASK_CANDIDATE_NOT_ACCESS(6103, "任务参与者配置不可用"),

    /**
     * 找不到该模型
     */
    MODEL_NOT_FOUND(6201,"找不到该模型"),
    /**
     * 模型已经存在
     */
    MODEL_ALREADY_EXISTS(6202,"模型已经存在"),


    /**
     * 要导入的流程模型文件不存在
     */
    MODEL_FILE_EMPTY(6203, "未检测到模型文件，请上传模型文件"),

    /**
     * 要导入的流程模型文件格式不正确
     */
    MODEL_FORMAT_NOT_SUPPORT(6204, "模型格式不正确，不能导入，仅支持【bpmn或.bpmn20.xml】文件导入"),

    /**
     * 流程模型文件无资源
     */
    MODEL_IMPORT_NO_RESOURCE(6204, "模型文件中未检测到资源，请重新绘制"),

    /**
     * 模型导入失败
     */
    MODEL_IMPORT_ERROR(6205, "模型导入失败"),

    MODEL_INNER_ERROR(6206, "模型操作出错"),

    DEPLOY_INNER_ERROR(6301, "部署发生错误"),

    DEFINITION_NOT_FIND(6401, "流程定义未找到"),

    DEFINITION_ALREADY_SUSPENDED(6402, "流程定义已经处于禁止状态"),

    NODE_UNDEFINED(6403, "未定义的节点类型"),

    NODE_AUTO_CONFIG_ERROR(6404, "自动任务未配置事件或者事件配置不合法"),

    NODE_INNER_ERROR(6405, "节点执行内部错误");



    private final int code;

    private final String message;

    FlowableExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
