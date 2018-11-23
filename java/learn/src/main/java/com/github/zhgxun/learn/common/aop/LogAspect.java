package com.github.zhgxun.learn.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 1. 切面方法
 * Aspect: 把当前类标识为一个切面供容器扫描
 * AfterReturning: 后置增强, 方法退出时执行
 * AfterThrowing: 异常抛出增强
 * After: final增强, 不管是抛出异常或者正常退出都会执行
 * Around: 环绕增强
 * <p>
 * 2. 切面PointCut切入点
 * execution: 切点函数, 用于匹配方法执行的连接点, 语法为:
 * execution(方法修饰符(可选) 返回类型 方法名 参数 异常模式(可选))
 * *  匹配任意字符, 但只能匹配一个元素
 * .. 匹配任意字符, 可以匹配任意多个元素, 表示类时, 必须和*联合使用
 * +  必须跟在类名后面, 如Horseman+, 表示类本身和继承或扩展指定类的所有
 * annotation: 表示标注了指定注解的目标类方法
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    // 指定切面切入点为AopController中的所有方法
    @Pointcut("execution (public * com.github.zhgxun.learn.controller.AopController.*(..))")
    public void normalLog() {
    }

    // 方法执行前切入, 即执行完该切面才会进入控制器代码执行段
    @Before("normalLog()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("方法执行前介入...");
        // 接收到请求, 记录请求内容 HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : {}", request.getRequestURL().toString());
        log.info("HTTP_METHOD : {}", request.getMethod());
        log.info("IP : {}", request.getRemoteAddr());
        log.info("CLASS_METHOD : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : {}", Arrays.toString(joinPoint.getArgs()));
    }

    @After("normalLog()")
    public void doAfter() {
        log.info("切面After运行");
    }

    // 方法异常时执行, 用于捕获一些异常信息, 不过通常普通的异常不需要太关心, 严重的异常直接终止了, 没必要再次捕获
    @AfterThrowing("normalLog()")
    public void doAfterThrowing() {
        log.error("切面方法异常抛出...");
    }

    // 环绕增强, 在Before之前运行, 在After之前运行, 如果业务需要, 可直接用Around代替Before和After, 但一般用其中一种即可
    @Around("normalLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        log.info("方法环绕开始...");
        try {
            Object o = joinPoint.proceed();
            log.info("环绕处理开始结束");
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
