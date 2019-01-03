package com.github.zhgxun.learn.event;

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
    public void send(OrderBean orderBean) {
        log.info("Start... {}", new Date());
        log.info("短信发送信息: {}", orderBean);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("End: {}", new Date());
    }
}
