package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.event.OrderBean;
import com.github.zhgxun.learn.event.OrderRegisterEvent;
import com.github.zhgxun.learn.event.Pay;
import com.github.zhgxun.learn.event.PayRegisterEvent;
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
        // 发布事件
        context.publishEvent(new OrderRegisterEvent(this, orderBean));
        log.info("订单发布结束, 异步处理中...");
        log.info("发布支付数据...");
        Pay pay = new Pay();
        pay.setId(new Random().nextInt(200));
        pay.setAmount(250.35);
        context.publishEvent(new PayRegisterEvent(this, pay));
        log.info("支付数据发布结束, 异步处理中...");
        return desc;
    }
}
