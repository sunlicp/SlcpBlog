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
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    /**
     * 参数错误
     */
    GLOBAL_PARAM_ERROR(4000, "参数错误"),
    /**
     * 获取当前用户失败
     */
    CURRENT_USER_FAIL(10001, "获取当前用户失败"),
    /**
     * 用户是超级管理员，不可以修改状态
     */
    UPDATE_USER_STATUS(10002, "用户是超级管理员，不可以修改状态"),
    /**
     * 用户是超级管理员，不可以修改密码
     */
    UPDATE_USER_PASSWORD(10003, "用户是超级管理员，不可以修改密码"),
    /**
     * 用户未登录，请登陆后进行访问
     */
    USER_NEED_LOGIN(11001, "用户未登录，请登陆后进行访问"),
    /**
     * 该用户已在其它地方登录
     */
    USER_MAX_LOGIN(11002, "该用户已在其它地方登录"),
    /**
     * 长时间未操作，自动退出
     */
    USER_LOGIN_TIMEOUT(11003, "长时间未操作，自动退出"),
    /**
     * 用户被禁11005用
     */
    USER_DISABLED(11004, "用户被禁11005用"),
    /**
     * 用户被锁定
     */
    USER_LOCKED(11005, "用户被锁定"),
    /**
     * 用户名或密码错误
     */
    USER_PASSWORD_ERROR(11006, "用户名或密码错误"),
    /**
     * 用户密码过期
     */
    USER_PASSWORD_EXPIRED(11007, "用户密码过期"),
    /**
     * 用户账号过期
     */
    USER_ACCOUNT_EXPIRED(11008, "用户账号过期"),
    /**
     * 没有该用户
     */
    USER_NOT_EXIST(11009, "没有该用户"),
    /**
     * 用户登录失败
     */
    USER_LOGIN_FAIL(11010, "用户登录失败"),
    /**
     * 验证码错误
     */
    VERIFY_CODE_ERROR(11011, "验证码错误"),
    /**
     * 用户已存在
     */
    USER_IS_EXIST(11012, "用户已存在"),
    /**
     * 无权访问
     */
    NO_AUTHENTICATION(1003006, "无权访问"),
    /**
     * 角色ID无效
     */
    ROLE_IS_NOT_EXIST(13001, "角色ID无效"),
    /**
     * 角色代码已存在
     */
    ROLE_IS_EXIST(13002, "角色代码已存在"),
    /**
     * 配置信息为空
     */
    CONFIG_ID_IS_NOT_EXIST(14001, "配置信息为空"),
    /**
     * 配置ID无效
     */
    CONFIG_IS_NOT_EXIST(14002, "配置ID无效"),
    /**
     * 配置ID已存在
     */
    CONFIG_IS_EXIST(14002, "配置ID已存在"),
    /**
     * 系统配置不允许修改
     */
    CONFIG_IS_SYSTEM(14003, "系统配置不允许修改"),
    /**
     * 系统配置不允许删除
     */
    CONFIG_IS_NOT_DELETE(14003, "系统配置不允许删除"),
    /**
     * 文件不存在
     */
    FILE_DOES_NOT_EXIST(16001, "文件不存在"),
    /**
     * 文件上传异常
     */
    FILE_UPLOAD_EXCEPTION(16002, "文件上传异常"),
    /**
     * 文件下载异常
     */
    FILE_DOWNLOAD_ABNORMAL(16003, "文件下载异常"),
    /**
     * 无效的资源ID
     */
    RESOURCE_NOT_FIND(12001, "无效的资源ID"),
    /**
     * 资源ID已存在
     */
    RESOURCE_IS_EXIST(12001, "资源ID已存在"),
    /**
     * 无效资源父节点ID
     */
    RESOURCE_PARENT_NOT_FIND(12002, "无效资源父节点ID"),
    /**
     * 无效资源父节点ID
     */
    RESOURCE_PARENT_INVALID(12003, "无效资源父节点ID"),
    /**
     * 该资源下有子资源，不能删除
     */
    RESOURCE_HAVE_SUB(12004, "该资源下有子资源，不能删除"),
    /**
     * 机构已存在
     */
    ORG_IS_EXIST(17001, "机构已存在"),
    /**
     * 机构不存在
     */
    ORG_NOT_EXIST(17002, "机构不存在"),
    /**
     * 机构下存在用户
     */
    ORG_HAVE_USER(17003, "机构下存在用户"),
    /**
     * 无效机构父节点ID
     */
    ORG_PID_ERROR(17004, "无效机构父节点ID"),
    /**
     * 父级节点禁止删除
     */
    ORG_TOP_FORBID(17005, "父级节点禁止删除"),
    /**
     * 机构下存在子机构
     */
    ORG_HAVE_BRANCH(17006, "机构下存在子机构"),
    /**
     * 停用原因不能为空
     */
    ORG_STOP_REASON(17007, "停用原因不能为空"),

    //字典管理
    /**
     * 父级ID无效
     */
    DICT_PID_ERROR(18001, "父级ID无效"),
    /**
     * ID无效
     */
    DICT_ID_ERROR(18002, "ID无效"),
    /**
     * 字典code已存在
     */
    DICT_CODE_EXIST(18003, "字典code已存在"),
    /**
     * 字典name已存在
     */
    DICT_NAME_EXIST(18004, "字典name已存在"),
    /**
     * 字典下存在数据
     */
    DICT_HAVE_DATA(18005, "字典下存在数据"),
    /**
     * 字典不存在
     */
    DICT_NOT_EXIST(18006, "字典不存在"),
    /**
     * 存在子节点
     */
    DICT_HAVE_SON(18007, "存在子节点"),
    //数据组
    /**
     * 数据组信息不存在
     */
    GROUP_ID_ERROR(19001, "数据组信息不存在"),
    /**
     * 数据组初始化无机构信息
     */
    GROUP_INIT_DATA_ERROR(19002, "数据组初始化无机构信息"),

    /**
     * 请求的数据不存在
     */
    DATA_NOT_EXIST(19003, "请求的数据不存在"),

    /**
     * 请求的数据已存在
     */
    DATA_ALREADY_EXIST(19004, "请求的数据已存在"),

    /**
     * 资格审查通道过期(前端要求)
     */
    CHECK_CHANNEL_OVERDUE(17000, "资格审查通道过期(前端要求)"),

    /**
     * 数据不能为空
     */
    DATA_CAN_NOT_BE_NULL(19005, "数据不能为空"),
    /**
     * 报名通道已关闭，已报名被驳回
     */
    APPLY_CHANNEL_OVERDUE_REJECT(19006, "报名通道已关闭，已报名被驳回"),

    /**
     * 您已被联合，不允许单独竞买
     */
    ALREADY_CONFIRM_UNION(19021, "您已被联合，不允许单独竞买"),

    /**
     * 同步数据异常
     */
    DATA_PULL_EXCEPTION(20001, "同步数据异常"),

    /**
     * 询问期未确认
     */
    ASK_UN_CONFIRM(19007, "询问未确认"),
    /**
     * 挂牌期无有效报价
     */
    LIST_NO_EFFECTIVE_QUOTE(19008, "挂牌期无有效报价"),
    /**
     * 询问期未确认
     */
    DS_QUALIFICATION(19009, "资格被取消"),
    /**
     * 询问期全部未确认
     */
    ASK_ALL_NO_CONFIRM(19010, "询问期全部未确认"),
    /**
     * 最高报价未达到底价
     */
    UNREACHED_MINIMUM_PRICE(19011, "最高报价未达到底价"),
    /**
     * 申请人全部未报价
     */
    ALL_NOT_QUOTE(19012, "申请人全部未报价"),
    /**
     * 资源已截止
     */
    TRADE_CLOSE(19020, "资源已截止");

    /**
     * 状态码
     */
    final int code;
    /**
     * 消息内容
     */
    final String msg;
}
