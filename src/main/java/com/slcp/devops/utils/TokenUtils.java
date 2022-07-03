package com.slcp.devops.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author Slcp
 */
public class TokenUtils {
    /**
     * 定义ID位移量
     */
    private static final Integer SHIFT_AMOUNT = 2;
    /**
     * 定义横向参数
     */
    private static final Long CROSSRANGE_AMOUNT = 3L;
    /**
     * 定义纵向参数
     */
    private static final Long PORTRAIT_AMOUNT = 21L;
    /**
     * 定义前随机数位数
     */
    private static final Integer Y_RANDOM = 4;
    /**
     * 定义后随机数位数
     */
    private static final Integer Q_RANDOM = 3;

    /**
     * 通过Id生成Token串
     * @param userId id
     * @return 值
     */
    public static String getToken(Long userId){
        //定义一个Buffer的Token串
        StringBuilder token = new StringBuilder();
        //先位移
        Long restoreForIdNum = changeForId(userId);
        //再混淆
        Long confusionId = confusionOperationId(restoreForIdNum);
        token.append(confusionId);
        //分割符
        token.append(":");
        //加入Y随机数
        token.append(getYRandomString(Y_RANDOM));
        //加入用户ID
        token.append(getUseridOnBase(userId));
        //加入Q随机数
        token.append(getQRandomString(Q_RANDOM));
        //返回Token
        return token.toString();
    }

    /**
     * Id移位
     * @param userId id
     * @return 值
     */
    public static Long changeForId(Long userId){
        String number = userId.toString();
        String[] array = number.split("");
        StringBuilder newNumber = new StringBuilder();
        //如果满足位移量，则按照位移量位移，如果不满足，则按照最低位移量位移
        if(array.length>SHIFT_AMOUNT){
            for(int run=SHIFT_AMOUNT;run<array.length;run++){
                newNumber.append(array[run]);
            }
            for(int run=0;run<SHIFT_AMOUNT;run++){
                newNumber.append(array[run]);
            }
        }else{
            if(array.length>1){
                for(int run=1;run<array.length;run++){
                    newNumber.append(array[run]);
                }
                newNumber.append(array[0]);
            }else{
                newNumber.append(array[0]);
            }
        }
        return Long.valueOf(newNumber.toString());
    }

    /**
     * Id归位
     * @param newUserId id
     * @return 值
     */
    public static Long restoreForId(Long newUserId){
        String number = newUserId.toString();
        String[] array = number.split("");
        StringBuilder newNumber = new StringBuilder();
        //如果满足位移量，则按照位移量位移，如果不满足，则按照最低位移量位移
        if(array.length>SHIFT_AMOUNT){
            for(int run=array.length-SHIFT_AMOUNT;run<array.length;run++){
                newNumber.append(array[run]);
            }
            for(int run=0;run<array.length-SHIFT_AMOUNT;run++){
                newNumber.append(array[run]);
            }
        }else{
            if(array.length>1){
                newNumber.append(array[array.length-1]);
                for(int run=0;run<array.length-1;run++){
                    newNumber.append(array[run]);
                }
            }else{
                newNumber.append(array[0]);
            }
        }
        return Long.valueOf(newNumber.toString());
    }

    /**
     * id混淆计算
     * @param newId id
     * @return 值
     */
    public static Long confusionOperationId(Long newId){
        //公式：aX+2ab-b = y
        return newId*CROSSRANGE_AMOUNT+2*PORTRAIT_AMOUNT*CROSSRANGE_AMOUNT-PORTRAIT_AMOUNT;
    }

    /**
     * id还原计算
     * @param confusionId id
     * @return  值
     */
    public static Long reductionOperationId(Long confusionId){
        //公式：(y+b-2ab)/a = x
        return (confusionId+PORTRAIT_AMOUNT-2*PORTRAIT_AMOUNT*CROSSRANGE_AMOUNT)/CROSSRANGE_AMOUNT;
    }

    /**
     * Y随机数，末尾为数字
     * @param length 长度
     * @return 数值
     */
    public static String getYRandomString(Integer length){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String strNext="0123456789";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length-1;i++){
            int number=random.nextInt(36);
            sb.append(str.charAt(number));
        }
        int numberNext=random.nextInt(10);
        sb.append(strNext.charAt(numberNext));
        return sb.toString();
    }

    /**
     * Q随机数，第一位为数字
     * @param length 长度
     * @return 数值
     */
    public static String getQRandomString(Integer length){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String strNext="0123456789";
        Random random=new Random();
        int numberNext=random.nextInt(10);
        StringBuilder sb = new StringBuilder();
        sb.append(strNext.charAt(numberNext));
        for(int i=1;i<length;i++){
            int number=random.nextInt(36);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * userId的Base64位算法
     * @param userId id
     * @return Base64位算法
     */
    public static String getUseridOnBase(Long userId){
        Base64.Encoder encoder = Base64.getEncoder();
        String text = userId.toString();
        final byte[] textByte = text.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(textByte);
    }

    /**
     * ID解密
     * @param userId id
     * @return 解密
     */
    public static String getBaseOnUserid(String userId){
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(userId), StandardCharsets.UTF_8);
    }

    /**
     * token校验及userID解析(防止token篡改)
     * @param token 令牌
     * @return 值
     */
    public static String JudgementToken(String token){
        //判断格式是否正确
        String pattern = ".*:.*";
        boolean isMatch = Pattern.matches(pattern, token);
        if(isMatch){
            //拆分
            String[] array = token.split(":");
            //ID算法串
            String truesIdStr = array[0];
            //用户ID串
            String userIdStr = array[1];
            if(StringUtil.isBlank(truesIdStr) || StringUtil.isBlank(userIdStr)){
                return "token is error";
            }
            //算法Id还原
            Long reductionId = reductionOperationId(Long.valueOf(truesIdStr));
            //算法Id归位
            Long truesId = restoreForId(reductionId);
            //切分用户ID串
            String baseId = userIdStr.substring(Y_RANDOM,userIdStr.length()-Q_RANDOM);
            String baseIdStr = getBaseOnUserid(baseId);
            Long userId = Long.valueOf(baseIdStr);
            if(truesId.equals(userId)){
                return truesId.toString();
            }else{
                return "token is error";
            }
        }else{
            return "token is error";
        }
    }

}

