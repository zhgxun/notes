package com.github.zhgxun.simple.hello.service.impl;

import com.github.zhgxun.simple.framework.annotation.Service;
import com.github.zhgxun.simple.hello.service.HelloService;

import java.util.Date;

@Service
public class HelloServiceImpl implements HelloService {

    public String parse(String name) {
        return String.format("Current Date: %s, request: %s", new Date(), name);
    }
}
