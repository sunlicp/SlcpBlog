package com.slcp.devops.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Slcp
 * @date Created in 2021/8/2 21:18
 * @description 查询热点缓存-注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoQueryCache {
    /**
     * 缓存过期时间
     * 单位(秒) 默认5秒
     */
    int expireTime() default 86400;

    /**
     * 缓存过期-随机数
     * 单位(秒) 默认0秒
     * 注：设置后实际过期时间，
     * 会在expireTime基础上继续累积(0~randomExpire)之间的秒数,防止缓存大量失效大面积穿透，造成雪崩
     */
    int randomExpire() default 0;

    /**
     * 是否存储为null 的返回
     * 注：防止缓存穿透，默认true,建议查询为空时，也进行缓存
     */
    boolean storageNull() default true;
}