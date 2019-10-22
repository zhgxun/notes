package com.github.zhgxun.learn.notes.config;

import com.github.zhgxun.learn.notes.LifeCycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanLifeCycleConfig {

    @Bean(initMethod = "init")
    public LifeCycle lifeCycle() {
        return new LifeCycle();
    }
}
