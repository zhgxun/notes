package com.github.zhgxun.learn.notes.spring.lookup;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();

        // 单一类型的依赖查找, 延迟查找
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        objectProvider.forEach(System.out::println);

        // 集合类型的依赖查找
        Map<String, Long> longMap = ((ListableBeanFactory) applicationContext).getBeansOfType(Long.class);
        System.out.println(longMap);

        // 层次性
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();


        applicationContext.close();
    }

    @Bean
    public String test() {
        return "Hello World.";
    }

    @Bean
    public Long a() {
        return 1L;
    }

    @Bean
    public Long b() {
        return 2L;
    }
}
