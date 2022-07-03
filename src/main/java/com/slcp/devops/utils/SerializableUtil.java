package com.slcp.devops.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.io.*;

/**
 * @author 4WD3Rock
 * @date Created in 2021/8/3 11:13
 * @description 序列化工具类
 */
public class SerializableUtil {
    private static final Log log = LogFactory.get();

    /**
     * 序列化
     *
     * @param obj 序列化对象
     * @throws NotSerializableException 类无实现序列化异常
     */
    public static byte[] serialize(Object obj) throws NotSerializableException {
        if (null == obj) {
            return null;
        }
        ObjectOutputStream objectOut = null;
        ByteArrayOutputStream byteArrayOut = null;
        try {
            byteArrayOut = new ByteArrayOutputStream();
            objectOut = new ObjectOutputStream(byteArrayOut);
            objectOut.writeObject(obj);
            byte[] b = byteArrayOut.toByteArray();
            objectOut.close();
            byteArrayOut.close();
            return b;
        } catch (NotSerializableException e) {
            //类无实现序列化异常
            throw e;
        } catch (Exception e) {
            log.info("SerializableUtil >> serialize exception", e);
            return null;
        } finally {
            closeOutput(objectOut, byteArrayOut);
        }
    }

    /**
     * 序列化
     *
     * @param obj          序列化对象
     * @param defaultValue 对象序列化失败后默认值
     * @throws NotSerializableException 类无实现序列化异常
     */
    public static byte[] serialize(Object obj, Object defaultValue) throws NotSerializableException {
        if (null == obj) {
            return null;
        }
        byte[] serializeValue = serialize(obj);
        if (null == serializeValue) {
            return serialize(defaultValue);
        }
        return serializeValue;
    }

    /**
     * 反序列化
     *
     * @throws NotSerializableException 类无实现序列化异常
     */
    public static Object deserialize(byte[] value) throws NotSerializableException {
        if (null == value) {
            return null;
        }
        ObjectInputStream objectIn = null;
        ByteArrayInputStream byteArrayIn = null;
        try {
            byteArrayIn = new ByteArrayInputStream(value);
            objectIn = new ObjectInputStream(byteArrayIn);
            return objectIn.readObject();
        } catch (NotSerializableException e) {
            throw e;
        } catch (Exception e) {
            log.info("SerializableUtil >> deserialize exception", e);
            return null;
        } finally {
            closeInput(objectIn, byteArrayIn);
        }
    }


    /**
     * 关闭输入流
     */
    private static void closeInput(ObjectInputStream objectIn, ByteArrayInputStream byteArrayIn) {
        try {
            if (null != objectIn) {
                objectIn.close();
            }
            if (null != byteArrayIn) {
                byteArrayIn.close();
            }
        } catch (Exception e) {
            log.info("SerializableUtil >> deserialize closeInput exception", e);
        }
    }

    /**
     * 关闭输出流
     */
    private static void closeOutput(ObjectOutputStream objectOut, ByteArrayOutputStream byteArrayOut) {
        try {
            if (null != objectOut) {
                objectOut.close();
            }
            if (null != byteArrayOut) {
                byteArrayOut.close();
            }
        } catch (Exception e) {
            log.info("SerializableUtil >> deserialize closeOutput exception", e);
        }
    }
}
