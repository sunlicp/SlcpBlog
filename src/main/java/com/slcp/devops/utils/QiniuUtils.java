package com.slcp.devops.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * 七牛云工具类
 * Zone.zone0:华东
 * Zone.zone1:华北
 * Zone.zone2:华南
 * Zone.zoneNa0:北美
 * ———http上传，自动识别上传区域——
 * Zone.httpAutoZone
 * ———https上传，自动识别上传区域—— //Zone.httpsAutoZone
 * Configuration cfg = new Configuration(Zone.zone2());其中Configuration中的Zone.zone2()可以改为Zone.zone0()
 * @author Slcp
 */
public class QiniuUtils {
    public static String accessKey = "";
    public static String secretKey = "";
    /**
     * 空间名称
     */
    public static String bucket = "";
    /**
     * 外链域名
     */
    public static String domain = "";

    public static void upload2Qiniu(String filePath,String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(filePath, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    /**
     * 上传文件
     * @param bytes 字节
     * @param fileName 文件名称
     * @return r
     */
    public static String upload2Qiniu(byte[] bytes, String fileName) {
        //构造一个带指定Zone对象的配置类 Zone.zone2:华南
        Configuration cfg = new Configuration(Zone.zone1());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(bytes, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            //返回文件完整路径
            return domain + putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
                return "";
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return "";
    }

    /**
     * 删除文件
     * @param fileName 文件名称
     */
    public static void deleteFileFromQiniu(String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, fileName);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}

