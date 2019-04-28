package com.github.zhgxun.learn.common.filter;

import com.github.zhgxun.learn.common.util.ApiX5ResponseUtil;
import com.github.zhgxun.learn.common.util.JsonUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/body")
public class X5RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = new X5RequestWrapper((HttpServletRequest) servletRequest);
        String params = request.getParameter("data");
        // 开始检查校验签名是否正确
        if (valid(params)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtil.toJson(ApiX5ResponseUtil.fail(500, "签名非法")));
            response.getWriter().flush();
            response.getWriter().close();
        } else {
            chain.doFilter(request, servletResponse);
        }
    }

    private boolean valid(String params) {
        return params.length() >= 10;
    }
}
