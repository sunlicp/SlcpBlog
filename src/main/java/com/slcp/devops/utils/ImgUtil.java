package com.slcp.devops.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * @author: SunXiaoWei
 * @description: 获取随机图片
 * @create: 2022-03-29 14:20:29
 **/
public class ImgUtil {

    public static byte[] getImg(String u) {
        URL url;
        try {
            url = new URL(u);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream in = conn.getInputStream();
            return readInputStream(in);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] readInputStream(InputStream ins) throws IOException {
        // TODO 自动生成的方法存根
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = ins.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        ins.close();

        return out.toByteArray();
    }


}
