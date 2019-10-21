package com.github.zhgxun.learn.notes.config;

import com.github.zhgxun.learn.notes.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Person person() {
        return new Person("张三", 18, "高年级学生");
    }
}
