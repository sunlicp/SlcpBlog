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
     * 应用版本号
     */
    public static final String DEVOPS_APP_VERSION = "3.1.1";

    /**
     * Spring 应用名 prop key
     */
    public static final String SPRING_APP_NAME_KEY = "spring.application.name";


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
    public static final String[] VALID_FILE_TYPE = {"xlsx", "zip", "pdf"};

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * json类型报文，UTF-8字符集
     */
    public static final String JSON_UTF8 = "application/json;charset=UTF-8";
    /**
     * 请求方式
     */
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_DELETE = "DELETE";

    /**
     * 随机图片
     */

    public static final String IMG_RANDOM = "https://source.unsplash.com/random/";

    /**
     * 获取地理位置
     */
    public static final String CITY_JSON = "https://pv.sohu.com/cityjson?ie=utf-8";

    public static String ACCESS_KEY = "-_QHommOvLHPgrPY3p52FU2qFilF4La-xqVdBMkQ";
    public static String SECRET_KEY = "i8IikvFR1DH7H9VxfVUf3LeYFmLpTwq8W34LoG7Q";
    /**
     * 空间名称
     */
    public static String BUCKET = "slcp-img";
    /**
     * 外链域名
     */
    public static String DOMAIN = "https://img.slcp.top/";

    /**
     * 获取试路径
     */
    public static String POEM_PATH = "https://v2.jinrishici.com/sentence";
    /**
     * 诗 请求头-key
     */
    public static String POEM_KEY = "X-User-Token";

    /**
     * 诗 请求头-value
     */
    public static String POEM_VALUE = "h26Z72ohlh395e4uI+Tkq4QMTgEBLBsb";

    /**
     * 返回诗
     */
    public static String POEM_CONTENT = "弃我去者，昨日之日不可留；乱我心者，今日之日多烦忧。";

}
