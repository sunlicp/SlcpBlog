package com.slcp.devops.utils;

import java.security.MessageDigest;

/**
 * @author: Slcp
 * @date: 2020/11/17 21:01
 * @code: 一生的挚爱
 * @description:
 */
public class Test {

    /**
     * MD5加码。32位
     * @param inStr s
     * @return r
     */
    public static String MD5(String inStr) {
            MessageDigest md5;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
            char[] charArray = inStr.toCharArray();
            byte[] byteArray = new byte[charArray.length];

            for (int i = 0; i < charArray.length; i++) {
                byteArray[i] = (byte) charArray[i];
            }

            byte[] md5Bytes = md5.digest(byteArray);

            StringBuilder hexValue = new StringBuilder();

        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }

            return hexValue.toString();
        }

    /**
     * 可逆的加密算法
     * @param inStr i
     * @return r
     */
    public static String KL(String inStr) {
            char[] a = inStr.toCharArray();
            for (int i = 0; i < a.length; i++) {
                a[i] = (char) (a[i] ^ 't');
            }
        return new String(a);
        }

    /**
     * 加密后解密
     * @param inStr i
     * @return r
     */
    public static String JM(String inStr) {
            char[] a = inStr.toCharArray();
            for (int i = 0; i < a.length; i++) {
                a[i] = (char) (a[i] ^ 't');
            }
        return new String(a);
        }

    /**
     * 测试主函数
     * @param args 参数
     */
    public static void main(String[] args) {
            String s = "sa";
            System.out.println("原始：" + s);
            System.out.println("MD5后：" + MD5(s));
            System.out.println("MD5后再加密：" + KL(MD5(s)));
            System.out.println("解密为MD5后的：" + JM(KL(MD5(s))));
        }


}
