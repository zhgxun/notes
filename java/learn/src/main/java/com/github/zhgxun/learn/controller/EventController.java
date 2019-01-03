package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.event.OrderBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Slf4j
public class EventController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/event/test")
    public String test(String desc) {
        log.info("接收到请求: {}", desc);
        OrderBean orderBean = new OrderBean();
        orderBean.setId(new Random().nextInt(100));
        orderBean.setDesc(desc);
        log.info("发布订单数据...");
        context.publishEvent(orderBean);
        log.info("发布结束, 异步处理中...");
        return desc;
    }
}
