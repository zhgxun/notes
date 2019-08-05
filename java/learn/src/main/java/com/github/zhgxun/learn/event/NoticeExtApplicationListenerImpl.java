package com.github.zhgxun.learn.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 会接收到所有 ApplicationEvent 相关的事件, 包括容器事件
 * 事件接收优先级比较高
 * <p>
 * 1. 如果不实现监听泛型, 则默认接收所有的相关事件, 需要选择感兴趣的事件, 即 if (event instanceof PayRegisterEvent) { }
 * 2. 如果指定了泛型的实现类型，比如 ApplicationListener<PayRegisterEvent>, 则只会监听到该事件的通知
 * <p>
 * 通用的容器事件
 * 1. {@link org.springframework.context.event.ContextStartedEvent}
 * 当使用 {@link ConfigurableApplicationContext#start()} (ApplicationContext的子接口）接口的start()方法启动ApplicationContext容器时触发该事件
 * 容器管理生命周期的Bean实例将获得一个指定的启动信号, 这在经常需要停止后重新启动的场合比较常见
 * <p>
 * 2. {@link org.springframework.context.event.ContextRefreshedEvent}
 * ApplicationContext容器初始化或刷新时触发该事件
 * 此处的初始化是指: 所有的 Bean 被成功装载, 后处理 Bean 被检测并激活, 所有 Singleton Bean 被预实例化, ApplicationContext容器已就绪可用
 * <p>
 * 3. {@link org.springframework.context.event.ContextStoppedEvent}
 * 当使用ConfigurableApplicationContext接口的stop()方法使ApplicationContext容器停止时触发该事件
 * 此处的停止, 意味着容器管理生命周期的 Bean 实例将获得一个指定的停止信号, 被停止的 Spring 容器可再次调用start()方法重新启动
 * <p>
 * 4. {@link org.springframework.context.event.ContextClosedEvent}
 * 当使用ConfigurableApplicationContext接口的close()方法关闭ApplicationContext时触发该事件
 * <p>
 * 5. {@link org.springframework.web.context.support.RequestHandledEvent}
 * Web相关事件, 只能应用于使用DispatcherServlet的Web应用
 * 在使用Spring作为前端的MVC控制器时, 当Spring处理用户请求结束后, 系统会自动触发该事件
 */
//@Component
@Slf4j
public class NoticeExtApplicationListenerImpl implements ApplicationListener {

    /**
     * 异步方式运行, 同步阻塞性能太低
     *
     * @param event 通用事件类型, 需要转化为感兴趣的事件
     */
    @Override
    @Async("taskEventExecutor")
    public void onApplicationEvent(ApplicationEvent event) {
        // 需要选择感兴趣的事件, 即该类能处理的事件, 其它事件不做处理
        if (event instanceof PayRegisterEvent) {
            log.info("当前事件是支付事件: {}, {}", event.getClass().getSimpleName(), new Date());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                log.error("error: {}", e.getMessage());
            } finally {
                log.info("支付事件处理结束: {}", new Date());
            }
        }
    }
}
