package com.github.zhgxun.learn.common.event;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void event() {
        log.info("发布支付数据...");
        Pay pay = new Pay();
        pay.setId(new Random().nextInt(200));
        pay.setAmount(250.35);
        context.publishEvent(new PayRegisterEvent(this, pay));
        log.info("支付数据发布结束, 异步处理中...");
    }
}
