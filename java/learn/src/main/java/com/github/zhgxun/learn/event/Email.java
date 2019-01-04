package com.github.zhgxun.learn.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Email {

    /**
     * 监听其实只需关注事件即可
     *
     * @param event 监听的事件
     */
    @Async
    @EventListener
    public void send(OrderRegisterEvent event) {
        log.info("Email Start... {}", new Date());
        log.info("邮件发送信息: {}", event.getOrderBean());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Email End: {}", new Date());
    }
}
