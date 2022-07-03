package com.slcp.devops.interceptor;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Slcp
 * @date: 2020/9/23 21:13
 * @code: 一生的挚爱
 * @description: 定义拦截类 拦截跳转地
 */
public class LoginInterceptor  extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (ObjectUtil.isEmpty(request.getSession().getAttribute("user"))){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
