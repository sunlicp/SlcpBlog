package com.slcp.devops.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.slcp.devops.entity.QqInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: Slcp
 * @date: 2020/11/17 21:30
 * @code: 一生的挚爱
 * @description:根据QQ号码获取QQ信息
 */
public class QQUtils {

        private static String getQqJsonStr(Long qqId) {
            StringBuilder jsonString = new StringBuilder();
            URLConnection connection;
            try {
                URL urlObject = new URL("http://r.pengyou.com/fcg-bin/cgi_get_portrait.fcg?uins="+qqId);
                connection = urlObject.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GBK"));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    jsonString.append(inputLine);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonString.substring("portraitCallBack(".length(),jsonString.length()-1);
        }
        public static QqInfo getQqInfo(Long qqId) {
            QqInfo qq = new QqInfo();
            String jsonString = getQqJsonStr(qqId);
            JSONObject jsonObject = JSON.parseObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(String.valueOf(qqId));
            qq.setId(qqId);
            qq.setName((String) jsonArray.get(6));
            qq.setAvatar((String) jsonArray.get(0));
            return qq;
        }
        public static void main(String args[]) {
//            System.out.println(getQqInfo(847064370L));
        }
    }
