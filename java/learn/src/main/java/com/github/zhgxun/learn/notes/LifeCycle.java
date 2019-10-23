package com.github.zhgxun.learn.notes;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

public class LifeCycle implements InitializingBean, DisposableBean, BeanNameAware, ApplicationContextAware {

    private Person person;

    // 1. 实例化bean, 生命周期的开始, 一般交给框架去处理
    public LifeCycle() {
        System.out.println("第一步. 实例化bean, 即是调用类本身的构造函数, 生命周期的开始 public LifeCycle()");
    }

    // 2. 设置bean在spring容器中的名字
    @Override
    public void setBeanName(String name) {
        System.out.println("第二步. 设置bean在spring容器中的名字 setBeanName");
    }

    // 3. 将内容上下文信息存储在bean中
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("第三步. 将内容上下文信息存储在bean中 setApplicationContext");
    }

    // 5. 执行指定的自定义初始化方法, 单独指定, 但是几乎没啥用
    @PostConstruct
    public void init() {
        System.out.println("第五步. 执行指定的自定义初始化方法 @PostConstruct");
    }

    // 6. 设置完属性后执行初始化操作
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第六步. 设置完属性后执行初始化操作 afterPropertiesSet");
    }

    // 主动设置或者获取属于业务部分的内容
    // 8. 设置属性内容, 这个未必在这一步, 可能在容器获取到对象之后进行操作
    public void setPerson(Person person) {
        this.person = person;
        System.out.println("第八步. 设置属性内容, setPerson");
    }

    // 9. 执行自身的业务方法, 比如获取设置完成的信息
    public Person getPerson() {
        System.out.println("第九步. 执行自身的业务方法, 比如获取设置完成的信息 getPerson");
        return person;
    }

    // 10. 销毁时执行, 一般看不到
    @Override
    public void destroy() throws Exception {
        System.out.println("第十步. 销毁时执行 destroy, 容器不显示关闭时无法看到");
    }
}
