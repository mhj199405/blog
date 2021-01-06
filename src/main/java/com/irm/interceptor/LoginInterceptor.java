package com.irm.interceptor;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter{
    @Override
    //这是一个拦截器，判断是否有用户登录，有用户登录则放行，否则重定向到登录页面
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("user") ==null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
