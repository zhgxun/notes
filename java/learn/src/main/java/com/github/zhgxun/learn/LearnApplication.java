package com.github.zhgxun.learn;

import com.github.zhgxun.learn.common.task.TaskApplicationListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LearnApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(LearnApplication.class);
        if (System.getProperty("spring.task.class") != null) {
            builder.listeners(new TaskApplicationListener()).run(args);
        } else {
            builder.run(args);
        }
    }
}
