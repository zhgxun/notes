package com.github.zhgxun.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
// @Async 注解默认不启用, 需要显示开启, 在任何地方都可以, 只需要开启一次即可, 为了简单, 在入口处开启即可
@EnableAsync
public class LearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnApplication.class, args);
    }
}
