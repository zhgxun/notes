package com.github.zhgxun.learn.common.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Message {

    @Async
    @EventListener
    public void send(OrderRegisterEvent event) {
        log.info("Message Start... {}", new Date());
        log.info("短信发送信息: {}", event.getOrderBean());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Message End: {}", new Date());
    }
}
