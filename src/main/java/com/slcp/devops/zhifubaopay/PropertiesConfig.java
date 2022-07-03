package com.slcp.devops.zhifubaopay;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * 应用启动加载文件
 * @author Slcp
 */
@Component
public class PropertiesConfig implements ApplicationListener {

    private static final Map<String, String> ALI_PROPERTIES_MAP = new HashMap<>();

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //监听启动程序
        if (event instanceof ApplicationReadyEvent) {
            init();
        }
    }


    /**
     * 加载支付宝配置文件properties
     */
    private static void init() {
        try {
            Properties prop = new Properties();
            InputStream ins = PropertiesConfig.class.getResourceAsStream("/alipay.properties");
            prop.load(ins);

            for (String key : prop.stringPropertyNames()) {
                PropertiesConfig.ALI_PROPERTIES_MAP.put(key,prop.getProperty(key));
                System.out.println(PropertiesConfig.ALI_PROPERTIES_MAP.get(key));
            }
        // 获得PathMatchingResourcePatternResolver对象
//            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//            try {
//                //加载resource文件(也可以加载resources)
//                Resource resources = resolver.getResource("classpath:/alipay.properties");
//                PropertiesFactoryBean config = new PropertiesFactoryBean();
//                config.setLocation(resources);
//                config.afterPropertiesSet();
//                Properties prop = config.getObject();
//                //循环遍历所有得键值对并且存入集合
//                for (String key : prop.stringPropertyNames()) {
//                    aliPropertiesMap.put(key, (String) prop.get(key));
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    /**
     * 根据key获取值
     * @param key key
     * @return 数据
     */
    public static String getKey (String key){
            return ALI_PROPERTIES_MAP.get(key);
        }

        public static void main (String[]args){
            init();
        }
    }