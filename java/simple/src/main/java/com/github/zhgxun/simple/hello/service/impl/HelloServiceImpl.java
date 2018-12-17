package com.github.zhgxun.simple.hello.service.impl;

import com.github.zhgxun.simple.framework.annotation.Service;
import com.github.zhgxun.simple.hello.service.HelloService;

import java.util.Date;

@Service
public class HelloServiceImpl implements HelloService {

    public String parse(String name) {
        return String.format("当前时间: %s, 接收到你的请求: %s", new Date(), name);
    }
}
