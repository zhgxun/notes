package com.github.zhgxun.learn.notes.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 这个是对所有bean而言的
 */
@Component
public class SelfBeanPostProcess implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycle")) {
            System.out.println("第四步. Bean: " + beanName + " 初始化前执行postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycle")) {
            System.out.println("第七步. Bean: " + beanName + "初始化后执行 postProcessAfterInitialization");
        }
        return bean;
    }
}
