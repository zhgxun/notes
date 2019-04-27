package com.github.zhgxun.learn.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;

public class X5RequestWrapper extends HttpServletRequestWrapper {

    public X5RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    // 对象时进入该方法
    @Override
    public Enumeration<String> getParameterNames() {
        Enumeration<String> enumeration = super.getParameterNames();
        ArrayList<String> list = Collections.list(enumeration);
        int index = list.indexOf("data");
        if (index >= 0) {
            // 对 data 进行 x5 解析
            String origin = list.get(index);
            String param = new String(Base64.getDecoder().decode(origin));
            list.add(index, param);
            return Collections.enumeration(list);
        } else {
            return enumeration;
        }
    }

    // url参数时一次进入
    @Override
    public String[] getParameterValues(String name) {
        if (name.equals("data")) {
            // 获取该参数的值
            String[] origin = super.getParameterValues(name);
            String param = new String(Base64.getDecoder().decode(origin[0]));
            return new String[]{param};
        }
        return super.getParameterValues(name);
    }
}
