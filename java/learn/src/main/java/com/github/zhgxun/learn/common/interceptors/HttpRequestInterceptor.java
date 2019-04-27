package com.github.zhgxun.learn.common.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

public class HttpRequestInterceptor implements HandlerInterceptor {

    // 进入 Controller 之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入 Controller 之前");
        System.out.println(request.getMethod());
        String data = request.getParameter("data");
        System.out.println(data);
        String p = new String(Base64.getDecoder().decode(data));
        System.out.println(p);
        request.setAttribute("data", p);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    // 调用 Controller 之后, 返回之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("调用 Controller 之后, 返回之前");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    // Controller 执行之后用于资源清理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Controller 执行之后用于资源清理");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
