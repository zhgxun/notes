package com.github.zhgxun.learn.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 支付相关事件
 */
@Getter
public class PayRegisterEvent extends ApplicationEvent {

    private Pay pay;

    public PayRegisterEvent(Object source, Pay pay) {
        super(source);
        this.pay = pay;
    }
}
