package com.github.zhgxun.learn.notes.spring.beans;

import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 传统的Ioc容器实现
 * 就是 {@link BeanInfo}
 * <p>
 * Java 内省(Introspector)深入理解 https://www.cnblogs.com/uu5666/p/8601983.html
 */
public class BeansUtil {

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // 传统的 java bean 实现, 第二个参数为排除处理的类, 比如父类 Object, 会有一个属性名为 class
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        // 获取所有属性描述
        Arrays.stream(beanInfo.getPropertyDescriptors()).forEach(p -> {
            Class<?> propertyType = p.getPropertyType();
            if (propertyType.equals(String.class)) {
                p.setPropertyEditorClass(String2IntegerPropertyEditor.class);
            }
        });

        Person person = new Person();
        BeanUtils.setProperty(person, "age", 10);
        System.out.println(person.getAge());
        System.out.println(BeanUtils.getProperty(person, "age"));

    }

    /**
     * 属性你编辑器实现
     */
    public static class String2IntegerPropertyEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(Integer.valueOf(text));
        }
    }
}
