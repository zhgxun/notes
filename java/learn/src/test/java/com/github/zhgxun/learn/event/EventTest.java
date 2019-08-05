package com.github.zhgxun.learn.event;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void event() {
        String desc = "模拟事件源";
//        log.info("接收到请求: {}", desc);
//        OrderBean orderBean = new OrderBean();
//        orderBean.setId(new Random().nextInt(100));
//        orderBean.setDesc(desc);
//        log.info("发布订单数据...");
//        // 发布事件
//        context.publishEvent(new OrderRegisterEvent(this, orderBean));
//        log.info("订单发布结束, 异步处理中...");
        log.info("发布支付数据...");
        Pay pay = new Pay();
        pay.setId(new Random().nextInt(200));
        pay.setAmount(250.35);
        context.publishEvent(new PayRegisterEvent(this, pay));
        log.info("支付数据发布结束, 异步处理中...");
    }
}
