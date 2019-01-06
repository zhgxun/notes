package com.github.zhgxun.learn.common;

import com.github.zhgxun.learn.common.bean.TestBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class TestUtil {
    public static void main(String[] args) {
        // 1. XmlBeanFactory 已经废弃, 但仍然可以使用
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("test-bean.xml"));
        TestBean bean = (TestBean) factory.getBean("testBean");
        System.out.println(bean);
        bean.setId(2);
        bean.setName("张三");
        bean.setAge(18);
        System.out.println(bean);

        // 2. 后续直接从xml文件创建bean的方式, 区别在于该种方式, 只有在 getBean() 时才会创建bean
        // 从spring 3.1版本后, xmlBeanFactory被表明为Deprecated
        // 推荐使用 DefaultListableBeanFactory 和 XmlBeanDefinitionReader 替换
        // 工厂还是默认的工厂, 父类工厂为空
        BeanFactory factory1 = new DefaultListableBeanFactory();
        // 构造读取器, 需要转型为特定的类型
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) factory1);
        reader.loadBeanDefinitions(new ClassPathResource("test-bean.xml"));
        TestBean bean1 = (TestBean) factory1.getBean("testBean");
        System.out.println(bean1);

        // 3. 这种方式也是推荐的方式, 该种方式读取配置文件时, 就创建 bean 的实例
        ApplicationContext context = new ClassPathXmlApplicationContext("test-bean.xml");
        TestBean bean2 = (TestBean) context.getBean("testBean");
        System.out.println(bean2);
    }
}
