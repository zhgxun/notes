package com.github.zhgxun.learn.notes.config;

import com.github.zhgxun.learn.notes.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 依赖外部环境决定加载bean
 */
@Configuration
public class ConditionConfig {

    @Conditional({LinuxCondition.class})
    @Bean
    public Person linuxPerson() {
        return new Person("linus", 50, "Linux创立者");
    }

    @Conditional({MacOSCondition.class})
    @Bean
    public Person macOSPerson() {
        return new Person("乔布斯", 50, "苹果创立者");
    }
}
