package com.github.zhgxun.learn.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Notice {

    @Async
    @EventListener
    public void notice(PayRegisterEvent event) {
        log.info("Pay Start... {}", new Date());
        log.info("支付成功回调信息: {}", event.getPay());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Pay End: {}", new Date());
    }
}
