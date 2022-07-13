package com.slcp.devops.interceptor;

import com.slcp.devops.utils.StringUtil;
import com.slcp.devops.utils.TokenUtils;
import io.swagger.models.HttpMethod;
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
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }

        String token = request.getHeader("Authorization");
        String[] s = token.split("_");
        if (StringUtil.isBlank(s[1]) || StringUtil.isBlank(s[0])){
            response.sendRedirect("/admin");
            return false;
        }
        if (!s[0].equals(TokenUtils.JudgementToken(s[1]))) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
