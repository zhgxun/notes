package com.github.zhgxun.learn.notes.config;

import com.github.zhgxun.learn.notes.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 最普通的注解方式
 * 将普通类加载的ioc容器中直接获取即可
 * <p>
 * 1. Configuration
 * 2. Bean id 默认为方法名, 如果不是多个接口同时实现, 无需关心采用泛型获取即可
 */
public class MainTest {

    public static void main(String[] args) {
        // 注解的方式启动spring容器
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }
}
