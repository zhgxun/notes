package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.common.event.OrderBean;
import com.github.zhgxun.learn.common.event.OrderRegisterEvent;
import com.github.zhgxun.learn.common.event.Pay;
import com.github.zhgxun.learn.common.event.PayRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * {@link org.springframework.context.ApplicationContextAware} 可以接管事件容器上下文
 * {@link ApplicationContext} 接口提供了 {@link ApplicationContext#publishEvent(Object)} 和
 * {@link ApplicationContext#publishEvent(ApplicationEvent)} 2个方法, 实现了Observe(观察者)设计模式的传播机制, 实现了对bean的传播
 * 通过ApplicationContext我们可以把系统中所有ApplicationEvent传播给系统中所有的ApplicationListener
 *
 * @see org.springframework.context.ApplicationContextAware
 * @see ApplicationContext
 * <p>
 * ApplicationEvent以及Listener是Spring为我们提供的一个事件监听, 订阅的实现, 内部实现原理是观察者设计模式
 * 设计初衷也是为了系统业务逻辑之间的解耦, 提高可扩展性以及可维护性
 * 事件发布者并不需要考虑谁去监听, 监听具体的实现内容是什么, 发布者的工作只是为了发布事件而已
 * @see org.springframework.context.ApplicationEvent
 * @see org.springframework.context.ApplicationListener
 * <p>
 * 在Spring内部中有多种方式实现监听如: @EventListener注解, 实现ApplicationListener泛型接口, 实现SmartApplicationListener接口等
 * @see org.springframework.context.event.EventListener
 * @see org.springframework.context.event.SmartApplicationListener 可以实现有序, 但是如果事件依赖顺序, 就成了同步操作, 还不如使用队列等其它方式实现, 或者事件循环往下广播即可
 * 比如必须确定先发送短信成功后再发送邮件, 则可以发送短信后再注册邮件事件, 但是这样就退化为同步操作, 使用不上异步的性能和低延迟了
 */
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
