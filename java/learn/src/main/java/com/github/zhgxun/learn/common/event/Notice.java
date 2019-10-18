package com.github.zhgxun.learn.common.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Notice {

    @Async("taskEventExecutor")
    @EventListener
    public void notice(Object e) {
        if (!(e instanceof PayRegisterEvent)) {
            return;
        }

        PayRegisterEvent event = (PayRegisterEvent) e;
        log.info("Pay Start... {}", new Date());
        log.info("支付成功回调信息: {}", event.getPay());
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e1) {
            log.error("error: {}", e1.getMessage());
        } finally {
            log.info("Pay End: {}", new Date());
        }
    }
}
