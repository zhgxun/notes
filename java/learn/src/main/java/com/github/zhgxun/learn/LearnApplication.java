package com.github.zhgxun.learn;

import com.github.zhgxun.learn.common.task.TaskApplicationListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ServletComponentScan
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
