package com.slcp.devops.utils;

import java.util.Base64;

/**
 * @author Slcp
 */
public class Base64Utils {
    /**
     * 加密
     * @param msg 信息
     * @return 数据
     */
    public static String encode(String msg){
        return Base64.getEncoder().encodeToString(msg.getBytes());
    }

    /**
     * 解密
     * @param msg 信息
     * @return 数据
     */
    public static String decode(String msg){
        return new String(Base64.getDecoder().decode(msg));
    }

    public static void main(String[] args) {
        String encode = Base64Utils.encode("123456");
        String decode = Base64Utils.decode(encode);
        System.out.println(encode);
        System.out.println(decode);
    }
}
