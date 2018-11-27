package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.common.bean.User;
import com.github.zhgxun.learn.common.aop.annotation.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Slf4j
public class AopController {

    @RequestMapping(path = "/method1", method = RequestMethod.GET)
    public User method1(String name, int age) {
        log.info("进入方法 method1...");
        if (age < 18) {
            throw new RuntimeException("未成年人禁止访问该页面");
        }
        return new User(name, age);
    }

    @Test
    @RequestMapping(path = "/method2", method = RequestMethod.GET)
    public String method2(String name) {
        return "接收到用户请求: " + name;
    }
}
