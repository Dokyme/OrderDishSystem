package com.odss.seu.controller;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossInterceptor extends HandlerInterceptorAdapter {

    //解决跨域访问的拦截器，以及delete put等method的options请求。
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","PUT,GET,POST,DELETE,OPTIONS");
        response.addHeader("Access-Control-Max-Age","100");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Allow-Credentials","false");
        return super.preHandle(request, response, handler);
    }
}
