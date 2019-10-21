package com.github.zhgxun.learn.notes.xml;

import com.github.zhgxun.learn.common.bean.TestBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * xml 方式启动spring, 不再推荐使用
 * 直接读取classpath下的配置即可
 */
public class ClassPathNote {

    public static void main(String[] args) {
        // xml 方式启动spring
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test-bean.xml");
        TestBean bean = (TestBean) context.getBean("testBean");
        System.out.println(bean);
    }
}
