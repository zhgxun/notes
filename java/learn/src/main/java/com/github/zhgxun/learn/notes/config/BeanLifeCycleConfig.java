package com.github.zhgxun.learn.notes.config;

import com.github.zhgxun.learn.notes.LifeCycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.github.zhgxun.learn.notes.config"})
public class BeanLifeCycleConfig {

    @Bean
    public LifeCycle lifeCycle() {
        return new LifeCycle();
    }
}
