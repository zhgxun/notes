package com.github.zhgxun.learn.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

// @AsyncConfigurerImpl 注解默认不启用, 需要显示开启, 在任何地方都可以, 只需要开启一次即可, 为了简单, 在入口处开启即可
// 如果要提供执行异步事件的线程池实现, 需要提供线程池
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfigurerImpl implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        log.info("初始化线程池...");
        // 有默认的线程池实现, 初始化16个线程, 如果默认的线程池满足需求, 可以不用配置
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(8);
        // 阻塞队列
        executor.setQueueCapacity(25);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
