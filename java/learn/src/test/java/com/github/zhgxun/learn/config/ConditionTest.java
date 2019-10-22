package com.github.zhgxun.learn.config;

import com.github.zhgxun.learn.notes.Person;
import com.github.zhgxun.learn.notes.config.ConditionConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ConditionTest {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        String[] beans = context.getBeanNamesForType(Person.class);
        System.out.println(Arrays.toString(beans));
    }
}
