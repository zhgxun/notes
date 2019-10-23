package com.github.zhgxun.learn.config;

import com.github.zhgxun.learn.notes.LifeCycle;
import com.github.zhgxun.learn.notes.Person;
import com.github.zhgxun.learn.notes.config.BeanLifeCycleConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifeCycleTest {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class);
        LifeCycle lifeCycle = context.getBean(LifeCycle.class);
        lifeCycle.setPerson(new Person("小李子", 10, "龟儿子"));
        System.out.println(lifeCycle.getPerson());
    }
}
