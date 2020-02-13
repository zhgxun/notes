package com.github.zhgxun.learn.notes.spring.beans;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Java 内省(Instrospector)深入理解
 * <p>
 * 内省是Java语言对Java Bean 类属性, 事件的一种缺省的处理方法
 * <p>
 * 1. Java Bean?
 * 一种特殊的类, 主要用于传递数据信息, 这种类中的方法主要用于访问私有的字段, 且方法名符合某种命名规则
 * 如果在两个模块之间传递信息, 可以将信息封装进 Java Bean 中, 这种对象称为值对象(Value Object), 或 "VO"
 * 方法较少, 且有一定的规则, 这些信息储存在类的私有变量中, 通过 set(), get() 获取
 * <p>
 * 2. JDK 内省类库
 * 2.1 {@link PropertyDescriptor}
 * 类表示 Java Bean 类通过存储器导出一个属性, 主要方法有
 * <p>
 * {@link PropertyDescriptor#getPropertyType()} 获取属性的 class 对象
 * {@link PropertyDescriptor#getReadMethod()} 获取用于读取属性值的方法
 * {@link PropertyDescriptor#getWriteMethod()} 获取用于写入属性值的方法
 * 还有一些set方法等
 * <p>
 * 对属性的设置也相当基础
 * <code>
 * Student student1 = new Student();
 * PropertyDescriptor descriptor = new PropertyDescriptor("person", Student.class);
 * Person person = new Person(1L, "李四", 18, 120.0);
 * descriptor.getWriteMethod().invoke(student1, person);
 * // 通过属性描述器获取
 * System.out.println(descriptor.getReadMethod().invoke(student1));
 * // 直接通过已经处理过的对象获取
 * System.out.println(student1.getPerson());
 * </code>
 * 即通过获取写方法直接反射进行赋值即可, 读取也是一样的道理直接取读方法反射执行
 * <p>
 * 2.2 {@link Introspector}
 * <p>
 * 将 Java Bean 中的属性进行封装起来进行操作
 * 程序把一个类当做Java Bean 来看, 就是调用 {@link Introspector#getBeanInfo(Class, int)} 方法得到这个对象封装后的结果信息
 * 后续操作直接遍历即可
 * <p>
 * 当然两种方式都需要获取类的属性描述器 {@link PropertyDescriptor} 来进行操作
 * <p>
 * 3. BeanUtils 工具
 * 由上述操作得知, 内省操作非常繁琐, 而且要考虑 Java 语言中的通用的和自定义的数据类型
 * 2.1 Apache 开发的工具类 {@link org.apache.commons.beanutils.BeanUtils}
 * <code>
 * <dependency>
 * <groupId>commons-beanutils</groupId>
 * <artifactId>commons-beanutils</artifactId>
 * <version>1.9.4</version>
 * </dependency>
 * </code>
 */
public class BeanTest {

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        String desc = "1,张三,18,30000";
        BeanSet.setBean(student, desc);
        System.out.println(student.getPerson());

        Student student1 = new Student();
        PropertyDescriptor descriptor = new PropertyDescriptor("person", Student.class);
        Person person = new Person(1L, "李四", 18, 120.0);
        descriptor.getWriteMethod().invoke(student1, person);
        // 通过属性描述器获取
        System.out.println(descriptor.getReadMethod().invoke(student1));
        // 直接通过已经处理过的对象获取
        System.out.println(student1.getPerson());
    }
}
