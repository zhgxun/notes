package com.github.zhgxun.learn.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

// 如果要提供执行异步事件的线程池实现, 需要提供线程池
// taskExecutor 为框架默认会寻找的任务线程池名称, 可以指定为事件自己的线程池名称
@Configuration
@Slf4j
public class AsyncConfigurer {

    @Bean("taskEventExecutor")
    public TaskExecutor getAsyncExecutor() {
        log.info("初始化线程池...");
        // 有默认的线程池实现, 初始化16个线程, 如果默认的线程池满足需求, 可以不用配置
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数, 默认为1
        executor.setCorePoolSize(2);

        // 初期如果不确定, 以下两个可以不设置, 线上观察是否有必要指定即可
        // 最大线程数, 默认为整数最大值
        executor.setMaxPoolSize(8);
        // 阻塞队列长度, 默认是 Integer 最大值
        executor.setQueueCapacity(25);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(10);
        executor.setThreadNamePrefix("TaskEvent-");

        executor.initialize();
        return executor;
    }
}
