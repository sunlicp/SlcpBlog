package com.slcp.devops.entity;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slcp.devops.api.ResultCode;
import com.slcp.devops.exception.DevopsException;
import com.slcp.devops.utils.StringUtil;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 4WD3Rock
 * @date Created in 2021/7/7 18:53
 * 统一后缀规则实现Wrapper构造
 * TODO 根据MyBatis Plus文档,补充构造扩展
 */
public class SqlWhereWrapper {
    private final static String SQL_REGEX = "'|%|--|insert|delete|select|count|group|union|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|sql";

    private static final String EQUAL = "_equal";
    private static final String NOT_EQUAL = "_notequal";
    private static final String LIKE = "_like";
    private static final String NOT_LIKE = "_notlike";
    private static final String GT = "_gt";
    private static final String LT = "_lt";
    private static final String DATE_GT = "_dategt";
    private static final String DATE_EQUAL = "_dateequal";
    private static final String DATE_LT = "_datelt";
    private static final String IS_NULL = "_null";
    private static final String NOT_NULL = "_notnull";
    private static final String IGNORE = "_ignore";
    private static final String UNDERLINE = "_";
    private static final String DATE_GT_SUFFIX = "Gt";
    private static final String DATE_LT_SUFFIX = "Lt";

    /**
     * 条件构造器
     *
     * @param queryParam   面向前端 queryParam 为模糊匹配 规则:columnName_datelt
     * @param queryWrapper 查询包装类
     */
    public static void buildCondition(Map<String, Object> queryParam, QueryWrapper<?> queryWrapper) {
        if (ObjectUtil.isEmpty(queryParam)) {
            return;
        }
        excludePageDTO(queryParam);
        queryParam.forEach((k, v) -> {
            if (ObjectUtil.hasEmpty(k, v)) {
                return;
            }
            if (k.endsWith(EQUAL)) {
                queryWrapper.eq(getColumn(k, EQUAL), v);
            } else if (k.endsWith(NOT_EQUAL)) {
                queryWrapper.ne(getColumn(k, NOT_EQUAL), v);
            } else if (k.endsWith(NOT_LIKE)) {
                queryWrapper.notLike(getColumn(k, NOT_LIKE), v);
            } else if (k.endsWith(GT)) {
                queryWrapper.gt(getColumn(k, GT), v);
            } else if (k.endsWith(LT)) {
                queryWrapper.lt(getColumn(k, LT), v);
            } else if (k.endsWith(DATE_GT)) {
                queryWrapper.gt(getColumn(k, DATE_GT), v);
            } else if (k.endsWith(DATE_EQUAL)) {
                queryWrapper.eq(getColumn(k, DATE_EQUAL), v);
            } else if (k.endsWith(DATE_LT)) {
                queryWrapper.lt(getColumn(k, DATE_LT), v);
            } else if (k.endsWith(IS_NULL)) {
                queryWrapper.isNull(getColumn(k, IS_NULL));
            } else if (k.endsWith(NOT_NULL)) {
                queryWrapper.isNotNull(getColumn(k, NOT_NULL));
            } else if (k.endsWith(IGNORE)) {
                String[] split = v.toString().split(",");
                queryWrapper.in(getColumn(k, IGNORE), (Object[]) split);
            } else {
                queryWrapper.like(getColumn(k, LIKE), v);
            }
        });
    }


    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param queryParam 查询条件
     */
    public static void excludePageDTO(Map<String, Object> queryParam) {
        Map<String, Object> excludeMap = new LinkedCaseInsensitiveMap();
        excludeMap.put("current", "current");
        excludeMap.put("size", "size");
        excludeMap.put("searchCount", "searchCount");
        excludeMap.put("total", "total");
        excludeMap.put("ascs", "ascs");
        excludeMap.put("descs", "descs");
        excludeMap.forEach((k, v) -> queryParam.remove(k));
    }

    /**
     * 将pageQuery转化成mybatis plus中的Page
     *
     * @param pageQuery 查询条件
     * @return IPage
     */
    public static <T> IPage<T> getIPage(Search pageQuery) {
        IPage<T> page = new Page<>(Convert.toInt(pageQuery.getPageNum(), 1), Convert.toInt(pageQuery.getPageSize(), 10));
        page.orders().addAll(OrderItem.ascs(filter(humpToUnderline(pageQuery.getAscs()))));
        page.orders().addAll(OrderItem.descs(filter(humpToUnderline(pageQuery.getDescs()))));
        return page;
    }

    /**
     * 获取数据库字段
     *
     * @param column  字段名
     * @param keyword 关键字
     * @return String
     */
    private static String getColumn(String column, String keyword) {
        return humpToUnderline(StrUtil.removeSuffix(column, keyword));
    }

    /**
     * 驼峰转下划线
     *
     * @param para 字符串
     * @return String
     */
    public static String humpToUnderline(String para) {
        if (StrUtil.isEmpty(para)) {
            return null;
        }
        para = lowerFirst(para);
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;
        for (int i = 0; i < para.length(); i++) {
            if (Character.isUpperCase(para.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 首字母变小写
     *
     * @param str 字符串
     * @return {String}
     */
    public static String lowerFirst(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'a');
            return new String(arr);
        }
        return str;
    }

    /**
     * 把SQL关键字替换为空字符串
     *
     * @param param 关键字
     * @return string
     */
    public static String filter(String param) {
        if (StrUtil.isEmpty(param)) {
            return null;
        }
        return param.replaceAll("(?i)" + SQL_REGEX, "");
    }

    /**
     * 请求参数转换成请求对象
     *
     * @param queryParam 请求参数map
     * @param beanClass  bean类型
     * @return 转换后对象
     */
    public static <T> T convertParamsMapToObject(Map<String, Object> queryParam, Class<T> beanClass) {
        Map<String, Object> resultMap = MapUtil.newHashMap();
        try {
            if (ObjectUtil.isEmpty(queryParam)) {
                return beanClass.newInstance();
            }
            excludePageDTO(queryParam);
            for (Map.Entry<String, Object> entry : queryParam.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                List<String> keyList = StrUtil.split(key, UNDERLINE);
                if (CollUtil.isNotEmpty(keyList)) {
                    String prefix = keyList.get(0);
                    if (keyList.size() == 2) {
                        String suffix = UNDERLINE + keyList.get(1);
                        if (StrUtil.equals(suffix, DATE_GT)) {
                            resultMap.put(prefix + DATE_GT_SUFFIX, value);
                        } else if (StrUtil.equals(suffix, DATE_LT)) {
                            resultMap.put(prefix + DATE_LT_SUFFIX, value);
                        } else if (StrUtil.equals(suffix, GT)) {
                            resultMap.put(prefix + DATE_GT_SUFFIX, value);
                        } else if (StrUtil.equals(suffix, LT)) {
                            resultMap.put(prefix + DATE_LT_SUFFIX, value);
                        } else if (StrUtil.equals(suffix, IGNORE)) {
                            String[] split = value.toString().split(",");
                            if (split.length > 0) {
                                List<String> resultList = Arrays.asList(split);
                                resultMap.put(prefix, resultList);
                            }
                        } else {
                            resultMap.put(prefix, value);
                        }
                    } else {
                        resultMap.put(prefix, value);
                    }

                }
            }
            if (CollUtil.isEmpty(resultMap)) {
                return beanClass.newInstance();
            }
        } catch (Exception e) {
            throw new DevopsException(ResultCode.FAILURE, "创建对象失败");
        }
        return JSONUtil.toBean(new JSONObject(resultMap), beanClass);
    }

    /**
     * 将pageQuery转化成mybatis plus中的Page
     *
     * @param search 查询条件
     * @return IPage
     */
    public static <T> IPage<T> getPage(Search search) {
        IPage<T> page = new Page<>(Convert.toInt(search.getPageNum(), 1), Convert.toInt(search.getPageSize(), 10));
        page.orders().addAll(OrderItem.ascs(filter(StringUtil.humpToUnderline(search.getAscs()))));
        page.orders().addAll(OrderItem.descs(filter(StringUtil.humpToUnderline(search.getDescs()))));
        return page;
    }
}
