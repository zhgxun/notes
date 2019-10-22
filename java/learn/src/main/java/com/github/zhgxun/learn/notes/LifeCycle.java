package com.github.zhgxun.learn.notes;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LifeCycle implements InitializingBean, DisposableBean, BeanNameAware, BeanPostProcessor, ApplicationContextAware {

    private Person person;

    // 1. 实例化bean, 生命周期的开始, 一般交给框架去处理
    public LifeCycle() {
        System.out.println("1. 实例化bean, 生命周期的开始");
    }

    // 2. 设置属性内容, 这个未必在这一步, 可能在容器获取到对象之后进行操作
    public void setPerson(Person person) {
        this.person = person;
        System.out.println("2. 设置属性内容, 这个未必在这一步, 可能在容器获取到对象之后进行操作");
    }

    // 3. 设置bean在spring容器中的名字
    @Override
    public void setBeanName(String name) {
        System.out.println("3. 设置bean在spring容器中的名字");
    }

    // 4. 将内容上下文信息存储在bean中
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("4. 将内容上下文信息存储在bean中");
    }

    // 5. 初始化之前处理
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("5. 初始化之前处理");
        return bean;
    }

    // 6. 设置完属性后执行初始化操作
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("6. 设置完属性后执行初始化操作");
    }

    // 7. 初始化之后的处理
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("7. 初始化之后的处理");
        return bean;
    }

    // 8. 执行指定的自定义初始化方法, 单独指定, 但是几乎没啥用
    public void init() {
        System.out.println("8. 执行指定的自定义初始化方法");
    }

    // 9. 执行自身的业务方法, 比如获取设置完成的信息
    public Person getPerson() {
        System.out.println("9. 执行自身的业务方法, 比如获取设置完成的信息");
        return person;
    }

    // 10. 销毁时执行, 一般看不到
    @Override
    public void destroy() throws Exception {
        System.out.println("10. 销毁时执行");
    }
}
