package com.github.zhgxun.learn.common.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/body")
public class X5RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入Filter拦截");
        chain.doFilter(new X5RequestWrapper((HttpServletRequest) request), response);
    }
}
