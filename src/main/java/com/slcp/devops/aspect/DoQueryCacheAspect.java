package com.slcp.devops.aspect;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.slcp.devops.config.DoQueryCache;
import com.slcp.devops.constant.DoQueryCacheContext;
import com.slcp.devops.utils.JedisCacheUtil;
import com.slcp.devops.utils.SerializableUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Slcp
 * @date Created in 2021/8/2 21:21
 * @description 查询热点缓存-切面
 */
@Aspect
@Component
public class DoQueryCacheAspect {
    private static final Log log = LogFactory.get();
    /**
     * Redis缓存Key 前缀
     */
    private static final String CACHE_KEY_PREFIX = "query.cache:";
    private static final String LOCK_KEY_PREFIX = "lock.";
    private static final String POINT = ".";
    @Resource
    private JedisCacheUtil jedisCacheUtil;
    /**
     * redisson client对象
     */
    @Resource
    private RedissonClient redisson;

    @Pointcut("@annotation(com.slcp.devops.config.DoQueryCache)")
    public void pointCut() {
    }

    /**
     * 拦截添加缓存注解的方法
     *
     * @see DoQueryCache
     */
    @Around("pointCut()&&@annotation(doQueryCache)")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint, DoQueryCache doQueryCache) throws Throwable {
        //注解为空
        if (ObjectUtil.isNull(doQueryCache)) {
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        }
        //方法实例
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        //方法类名
        String className = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        //方法名
        String methodName = method.getName();
        log.debug("[ CacheAspect ] >> notReadCacheFlag:{} , refreshCacheFlag :{} , method:{} ",
                DoQueryCacheContext.notReadCacheFlag.get(), DoQueryCacheContext.refreshCacheFlag.get(), className + methodName);
        //已设置-不需要读取缓存
        if (null != DoQueryCacheContext.notReadCacheFlag.get() && DoQueryCacheContext.notReadCacheFlag.get()) {
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        }
        //生成缓存key
        String cacheKey = this.getCacheKey(className, methodName, proceedingJoinPoint);
        //已设置-需要刷新缓存
        if (null != DoQueryCacheContext.refreshCacheFlag.get() && DoQueryCacheContext.refreshCacheFlag.get()) {
            Object resultValue = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            //刷新缓存
            jedisCacheUtil.putByte(cacheKey, SerializableUtil.serialize(resultValue, cacheKey), this.getExpireTime(doQueryCache));
            return resultValue;
        }
        Object value = null;
        //缓存中读取值
        byte[] cacheByteValue = jedisCacheUtil.getByte(cacheKey);
        if (null != cacheByteValue && cacheByteValue.length > 0) {
            //反序列化对象
            value = SerializableUtil.deserialize(cacheByteValue);
        }
        //缓存中存在并且不为空-直接返回
        if (null != value && !cacheKey.equals(value.toString())) {
            log.info("[ CacheAspect ] >> [ get from cache in first ] method:{} , cacheKey:{}", className + methodName, cacheKey);
            return value;
        }
        //如果设置了允许存储null值，若缓存key存在，并且value与自定义key相同 > 则直接返回 null
        if (doQueryCache.storageNull() && jedisCacheUtil.exists(cacheKey)) {
            log.info("[ CacheAspect ] >> [ get from cache in first value is null ] method:{} , cacheKey:{}", className + methodName, cacheKey);
            return null;
        }
        //若缓存中不存在 > 则执行方法，并重入缓存
        //加锁防止大量穿透
        RLock lock = redisson.getLock(LOCK_KEY_PREFIX + cacheKey);
        lock.lock();
        try {
            //二次尝试从缓存中读取
            byte[] cacheByteValueSecond = jedisCacheUtil.getByte(cacheKey);
            if (null != cacheByteValueSecond && cacheByteValueSecond.length > 0) {
                //反序列化对象
                value = SerializableUtil.deserialize(cacheByteValueSecond);
            }
            //缓存中存在并且不为空-直接返回
            if (null != value && !cacheKey.equals(value.toString())) {
                log.info("[ CacheAspect ] >> [ get from cache in second ] method:{} , cacheKey:{}", className + methodName, cacheKey);
                return value;
            }
            //如果设置了允许存储null值，若缓存key存在，并且value与自定义key相同 > 则直接返回 null
            if (doQueryCache.storageNull() && jedisCacheUtil.exists(cacheKey)) {
                log.info("[ CacheAspect ] >> [ get from cache in second value is null ] method:{} , cacheKey:{}", className + methodName, cacheKey);
                return null;
            }
            //执行方法-并获得返回值
            value = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

            //返回值不为空-存入缓存并返回
            if (null != value) {
                //存入缓存
                jedisCacheUtil.putByte(cacheKey, SerializableUtil.serialize(value, cacheKey), this.getExpireTime(doQueryCache));
                return value;
            }
            //返回值为空-是否设置需要存储空对象
            if (doQueryCache.storageNull()) {
                //存入缓存,空返回值时value也存储key
                jedisCacheUtil.putByte(cacheKey, SerializableUtil.serialize(cacheKey), this.getExpireTime(doQueryCache));
                return null;
            }
            return null;
        } finally {
            //解锁
            lock.unlock();
            log.info("[ CacheAspect ] >> un lock method:{} , cacheKey:{}", className + methodName, cacheKey);
        }
    }

    /**
     * 生成缓存key
     *
     *
     */
    private String getCacheKey(String className, String keyPrefix, ProceedingJoinPoint proceedingJoinPoint) {
        //缓存key前缀
        //方法全路径（类名+方法名）
        String methodPath = className + POINT + keyPrefix;
        //若方法参数为空
        if (proceedingJoinPoint.getArgs() == null || proceedingJoinPoint.getArgs().length == 0) {
            return CACHE_KEY_PREFIX + keyPrefix + POINT + DigestUtil.md5Hex(methodPath);
        }
        //参数序号
        int i = 0;
        //按照参数顺序,拼接方法参数
        Map<String, Object> paramMap = new LinkedHashMap<>(proceedingJoinPoint.getArgs().length);
        for (Object obj : proceedingJoinPoint.getArgs()) {
            i++;
            if (ObjectUtil.isNotNull(obj)) {
                paramMap.put(obj.getClass().getName() + i, obj);
            } else {
                paramMap.put("NULL" + i, "NULL");
            }
        }
        String paramJson = JSONUtil.toJsonStr(paramMap);
        log.debug("[ DoQueryCacheAspect ] >> param-JSON:{}", JSONUtil.toJsonStr(paramMap));
        return CACHE_KEY_PREFIX + keyPrefix + POINT + DigestUtil.md5Hex(paramJson);
    }

    /**
     * 计算过期时间 如果缓存设置了需要延迟失效
     * 取设置的延迟时间1-2倍之间的一个随机值作为真正的延迟时间值
     */
    private int getExpireTime(DoQueryCache doQueryCache) {
        int randomExpire = 0;
        //若设置>0 , 失效时间加上（0~设置）的值内的随机数
        if (doQueryCache.randomExpire() > 0) {
            randomExpire = RandomUtil.randomInt(doQueryCache.randomExpire());
        }
        return doQueryCache.expireTime() + randomExpire;
    }
}