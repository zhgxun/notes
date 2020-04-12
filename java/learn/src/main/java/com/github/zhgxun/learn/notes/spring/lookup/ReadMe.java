package com.github.zhgxun.learn.notes.spring.lookup;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;

import javax.naming.Name;
import java.util.function.Supplier;

/**
 * 1. 依赖查找的前世今生
 * <p>
 * 1.1 单一类型依赖查找
 * JNDI {@link javax.naming.Context#lookup(Name)}
 * {@link java.beans.beancontext.BeanContext}
 * <p>
 * 1.2 集合类型依赖查找
 * {@link java.beans.beancontext.BeanContext}
 * {@link org.springframework.beans.factory.ListableBeanFactory}
 * <p>
 * 1.3 层次性依赖查找
 * {@link java.beans.beancontext.BeanContext}
 * {@link org.springframework.beans.factory.HierarchicalBeanFactory}
 * {@link org.springframework.beans.factory.BeanFactoryUtils}
 * <p>
 * 依赖查找安全性对比
 * 依赖查找类型
 * {@link org.springframework.beans.factory.BeanFactory#getBean(String)} 不安全
 * {@link ObjectFactory#getObject()} 不安全
 * {@link org.springframework.beans.factory.ObjectProvider#getIfAvailable(Supplier)} 安全
 * 集合类型查找
 * {@link org.springframework.beans.factory.ListableBeanFactory#getBeansOfType(Class)} 安全
 * {@link ObjectProvider#stream()} 安全
 * 这里的安全和不安全是是否可抛出异常
 * <p>
 * 内建的依赖
 * {@link org.springframework.context.annotation.AnnotationConfigUtils}
 *
 * 区别
 * {@link org.springframework.beans.factory.BeanFactory}
 * {@link org.springframework.beans.factory.FactoryBean}
 * {@link ObjectFactory}
 */
public class ReadMe {
}
