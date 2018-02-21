package com.flight.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 注解后返回json格式数据
@RestController
public class HelloController {

    // 注解路由
    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public String say(@RequestParam(value = "message", required = false, defaultValue = "Hello") String message) {
        return "message: " + message;
    }
}
