package com.github.zhgxun.learn.common.filter;

import com.sun.net.httpserver.Headers;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/body")
public class X5RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入Filter拦截");

        HttpServletResponse response1 = (HttpServletResponse) response;
        System.out.println(response1.getHeaderNames());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"header\":{\"code\":500, \"desc\":\"签名非法\"}, \"body\":{}}");
        response.getWriter().flush();
        response.getWriter().close();
//        chain.doFilter(new X5RequestWrapper((HttpServletRequest) request), response);
    }
}
