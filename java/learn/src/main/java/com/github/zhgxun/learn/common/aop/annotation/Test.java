package com.github.zhgxun.learn.common.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Retention 指定该注解存活的生命周期, 通常有源代码级别, Class文件和运行时级别
 * Target 注解可以用在哪些属性上, 主要的有
 *     TYPE 用在类上
 *     FIELD 用在属性上
 *     METHOD 用在方法上
 *     ... 还包括参数等
 */
// 该自定义注解目前是未生效的, 需要在运行时等时机使其实现并生效
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}
