package com.slcp.devops.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.redisson.api.*;
import org.redisson.client.codec.ByteArrayCodec;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Slcp
 * @date Created in 2021/8/3 11:49
 * @description 基于Redisson客户端实现 redis 缓存工具类
 */
@Component
public class JedisCacheUtil {
    private static final Log log = LogFactory.get();
    /**
     * 默认缓存时间(秒)
     */
    private static final Long DEFAULT_EXPIRED = 5L;

    /**
     * redisson client对象
     */
    @Autowired
    private RedissonClient redisson;

    /**
     * 读取缓存
     *
     * @param key 缓存key
     * @param <T>
     * @return 缓存返回值
     */
    public <T> T get(String key) {
        RBucket<T> bucket = redisson.getBucket(key);
        return bucket.get();
    }

    /**
     * 以string的方式读取缓存
     *
     * @param key 缓存key
     * @return 缓存返回值
     */
    public String getString(String key) {
        RBucket<String> bucket = redisson.getBucket(key, StringCodec.INSTANCE);
        return bucket.get();
    }

    /**
     * 以string的方式读取缓存
     *
     * @param key 缓存key
     * @return 缓存返回值
     */
    public byte[] getByte(String key) {
        RBucket<byte[]> bucket = redisson.getBucket(key, ByteArrayCodec.INSTANCE);
        return bucket.get();
    }

    /**
     * 设置缓存（注：redisson会自动选择序列化反序列化方式）
     *
     * @param key   缓存key
     * @param value 缓存值
     * @param <T>
     */
    public <T> void put(String key, T value) {
        RBucket<T> bucket = redisson.getBucket(key);
        bucket.set(value, DEFAULT_EXPIRED, TimeUnit.SECONDS);
    }

    /**
     * 以指定的序列化方式设置缓存
     *
     * @param key
     * @param value
     * @param codec
     * @param <T>
     */
    public <T> void put(String key, T value, Codec codec) {
        RBucket<T> bucket = redisson.getBucket(key, codec);
        bucket.set(value, DEFAULT_EXPIRED, TimeUnit.SECONDS);
    }

    /**
     * 以指定的序列化方式设置缓存
     *
     * @param key
     * @param value
     * @param expired
     * @param codec
     * @param <T>
     */
    public <T> void put(String key, T value, long expired, Codec codec) {
        RBucket<T> bucket = redisson.getBucket(key, codec);
        bucket.set(value, expired, TimeUnit.SECONDS);
    }

    /**
     * 以指定的序列化方式读取缓存
     *
     * @param key
     * @param codec
     * @return
     */
    public <T> T get(String key, Codec codec) {
        RBucket<T> bucket = redisson.getBucket(key, codec);
        return bucket.get();
    }

    /**
     * 以string的方式设置缓存
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        RBucket<String> bucket = redisson.getBucket(key, StringCodec.INSTANCE);
        bucket.set(value, DEFAULT_EXPIRED, TimeUnit.SECONDS);
    }

    /**
     * 以string的方式保存缓存（与其他应用共用redis时需要使用该函数）
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间
     */
    public void putString(String key, String value, long expired) {
        RBucket<String> bucket = redisson.getBucket(key, StringCodec.INSTANCE);
        bucket.set(value, expired <= 0 ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 以byte的方式保存缓存（与其他应用共用redis时需要使用该函数）
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间
     */
    public void putByte(String key, byte[] value, long expired) {
        RBucket<byte[]> bucket = redisson.getBucket(key, ByteArrayCodec.INSTANCE);
        bucket.set(value, expired <= 0 ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 如果不存在则写入缓存（string方式，不带有redisson的格式信息）
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间
     */
    public boolean putStringIfAbsent(String key, String value, long expired) {
        RBucket<String> bucket = redisson.getBucket(key, StringCodec.INSTANCE);
        return bucket.trySet(value, expired <= 0 ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 如果不存在则写入缓存（string方式，不带有redisson的格式信息）（不带过期时间，永久保存）
     *
     * @param key   缓存key
     * @param value 缓存值
     */
    public boolean putStringIfAbsent(String key, String value) {
        RBucket<String> bucket = redisson.getBucket(key, StringCodec.INSTANCE);
        return bucket.trySet(value);
    }

    /**
     * Put concurrentMap
     *
     * @param key      缓存key
     * @param mapKey   map key
     * @param mapValue map value
     */
    public void putMap(String key, String mapKey, String mapValue) {
        ConcurrentMap<String, String> concurrentMap = redisson.getMap(key);
        concurrentMap.put(mapKey, mapValue);
    }

    /**
     * ¬
     * Returns map instance by name
     *
     * @param key 缓存key
     * @return
     */
    public ConcurrentMap<String, String> getHashMap(String key) {
        return redisson.getMap(key);
    }

    /**
     * 根据用户id获取存储到hash中的值（string方式）
     *
     * @param redisKey 用户id
     * @return
     */
    public ConcurrentMap<String, String> getHashMapString(String redisKey) {
        return redisson.getMap(redisKey, StringCodec.INSTANCE);
    }

    /**
     * Remove map value
     *
     * @param key    缓存key
     * @param mapKey map key
     */
    public void removeHashMapValue(String key, String mapKey) {
        ConcurrentMap<String, String> concurrentMap = redisson.getMap(key);
        concurrentMap.remove(mapKey);
    }

    /**
     * 设置缓存
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间
     * @param <T>     类型
     */
    public <T> void put(String key, T value, long expired) {
        RBucket<T> bucket = redisson.getBucket(key);
        bucket.set(value, expired <= 0 ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 计数器自增（+1），并返回计算前的原值
     * 如果key不存在则按当前值为0计算
     *
     * @param key     缓存key
     * @param expired 过期时间
     */
    public long getAndIncrement(String key, long expired) {
        RAtomicLong atomicLong = redisson.getAtomicLong(key);
        long num = atomicLong.getAndIncrement();
        atomicLong.expire(expired, TimeUnit.SECONDS);
        return num;
    }

    /**
     * 计数器累加指定的值，并返回计算前的原值
     * 如果key不存在则按当前值为0计算
     *
     * @param key     缓存key
     * @param expired 过期时间
     * @param delta   本次累加的值
     */
    public long getAndIncrement(String key, long delta, long expired) {
        RAtomicLong atomicLong = redisson.getAtomicLong(key);
        long num = atomicLong.getAndAdd(delta);
        atomicLong.expire(expired, TimeUnit.SECONDS);
        return num;
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public void remove(String key) {
        redisson.getBucket(key).delete();
    }

    /**
     * 移除缓存
     *
     */
    public void removeAll() {
        redisson.getKeys().flushall();
    }

    /**
     * 判断缓存是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisson.getBucket(key).isExists();
    }


    /**
     * redisson的RList对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RList<T> getRedisList(String key) {
        return redisson.getList(key);
    }

    /**
     * redisson的RList对象(string方式)
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RList<T> getRedisListString(String key) {
        return redisson.getList(key, StringCodec.INSTANCE);
    }

    /**
     * redisson的RMapCache对象
     *
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> RMapCache<K, V> getRedisMap(String key) {
        return redisson.getMapCache(key);
    }

    /**
     * redisson的RMapCache对象(string方式)
     *
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> RMapCache<K, V> getRedisMapString(String key) {
        return redisson.getMapCache(key, StringCodec.INSTANCE);
    }

    /**
     * redisson的RSET对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RSet<T> getRedisSet(String key) {
        return redisson.getSet(key);
    }

    /**
     * redisson的RSET对象（string方式，不带有redisson的格式信息）
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RSet<T> getRedisSetString(String key) {
        return redisson.getSet(key, StringCodec.INSTANCE);
    }

    /**
     * redisson的Lock对象
     *
     * @param key
     * @return
     */
    public RLock getRedisLock(String key) {
        return redisson.getLock(key);
    }

    /**
     * redisson的RSortedSet对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RSortedSet<T> getSortedSet(String key) {
        return redisson.getSortedSet(key);
    }

    /**
     * redisson的RSortedSet对象(指定序列化实现)
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RSortedSet<T> getSortedSet(String key, Codec codec) {
        return redisson.getSortedSet(key, codec);
    }

    /**
     * redisson的RScoredSortedSet对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RScoredSortedSet<T> getScoredSortedSet(String key) {
        return redisson.getScoredSortedSet(key);
    }

    /**
     * redisson的RScoredSortedSet对象(指定序列化实现)
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RScoredSortedSet<T> getScoredSortedSet(String key, Codec codec) {
        return redisson.getScoredSortedSet(key, codec);
    }
}