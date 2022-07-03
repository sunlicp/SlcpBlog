package com.slcp.devops.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author: SunXiaoWei
 * @create: 2022/2/1810:10
 **/
@Component
public class ColorUtil {
    public static String getRandColor() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;
        return "#" + r + g + b;
    }
}
