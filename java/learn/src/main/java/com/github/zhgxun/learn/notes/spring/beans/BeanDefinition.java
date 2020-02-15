package com.github.zhgxun.learn.notes.spring.beans;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanDefinition} 是 Spring Framework 中定义 Bean 的配置元信息的接口, 包含
 * Bean 的类名
 * Bean 的行为配置元素, 如作用域, 自动绑定的模式, 生命周期回调等
 * 其它 Bean 引用, 又可称合作者或者依赖
 * 配置设置, 比如 Bean 的属性
 * <p>
 * <p>
 * 元信息
 * Class                    全类名, 必须是具体类不能是抽象类
 * Name                     名称或者id
 * Scope                    作用域
 * Constructor arguments    构造器参数
 * Properties               属性设置
 * Autowiring mode          自动绑定模式
 * Lazy initialization mode 延迟初始化模式
 * Initiation method        初始化回调方法名称
 * Destruction method       销毁回调方法名称
 * <p>
 * BeanDefinition 构建
 * 通过 {@link org.springframework.beans.factory.support.BeanDefinitionBuilder}
 * 通过 {@link org.springframework.beans.factory.support.AbstractBeanDefinition}
 * <p>
 * 注册 Spring Bean
 * XML配置元信息
 * <bean name=""></bean>
 * <p>
 * Java注解配置元信息
 * {@link org.springframework.context.annotation.Bean}
 * {@link org.springframework.stereotype.Component}
 * {@link org.springframework.context.annotation.Import}
 * <p>
 * Java API 配置元信息
 * 命名方式 {@link org.springframework.beans.factory.support.BeanDefinitionRegistry#registerBeanDefinition(String, org.springframework.beans.factory.config.BeanDefinition)}
 * 非命名方式 {@link org.springframework.beans.factory.support.BeanDefinitionReaderUtils#registerWithGeneratedName(AbstractBeanDefinition, BeanDefinitionRegistry)}
 * 配置类方式 {@link org.springframework.context.annotation.AnnotatedBeanDefinitionReader#register(Class[])}
 * <p>
 * 实例化 Spring Bean
 * Bean 的实例化
 * 常规方式
 * 通过构造器(配置元信息: XML, Java注解，Java API)
 * 通过静态工厂方法
 * 通过 Bean 工厂方法
 * 通过 {@link org.springframework.beans.factory.FactoryBean}
 * <p>
 * 特殊方式
 * 通过 {@link org.springframework.beans.factory.serviceloader.ServiceListFactoryBean}
 * 通过 {@link org.springframework.beans.factory.config.AutowireCapableBeanFactory#createBean(Class)}
 * 通过 {@link BeanDefinitionRegistry#registerBeanDefinition(String, org.springframework.beans.factory.config.BeanDefinition)}
 * <p>
 * 初始化 Spring Bean
 * {@link javax.annotation.PostConstruct}
 * 实现 {@link InitializingBean#afterPropertiesSet()}
 * 自定义初始化方法
 * 1. XML配置 <bean init-method=''></bean>
 * 2. Java 注解 {@link org.springframework.context.annotation.Bean} initMethod=''
 * 3. Java API {@link AbstractBeanDefinition#setInitMethodName(String)}
 * <p>
 * 需要留意执行顺序 底层的优先执行, 低级别的会覆盖高阶别的同类属性
 */
public class BeanDefinition {

    public static void main(String[] args) {
        // 构建一个 Bean
        // 1. 通过 BeanDefinitionBuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Person.class);
        beanDefinitionBuilder.addPropertyValue("userId", 2L)
                .addPropertyValue("name", "李四")
                .addPropertyValue("age", 18)
                .addPropertyValue("amount", 3.0);

        /**
         * 该类实现了
         * {@link BeanDefinitionRegistry#registerBeanDefinition(String, org.springframework.beans.factory.config.BeanDefinition)}
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanDefinition.class);

        // 2. 通过 AbstractBeanDefinition 构建
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(Person.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("userId", 2L)
                .add("name", "李四")
                .add("age", 18)
                .add("amount", 3.0);
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }
}
