package com.github.zhgxun.learn;

import com.github.zhgxun.learn.common.converter.MongoSerialConverter;
import com.github.zhgxun.learn.common.task.TaskApplicationListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

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

    // MongoCustomConversions
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        System.out.println("Mongo转换器...");
        return new MongoCustomConversions(Arrays.asList(new MongoSerialConverter()));
    }
}
