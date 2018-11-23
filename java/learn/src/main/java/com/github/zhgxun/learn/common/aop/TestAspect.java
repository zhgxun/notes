package com.github.zhgxun.learn.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TestAspect {

    // 切入注解, 即是只对使用了该注解的方法生效
    @Pointcut("@annotation(com.github.zhgxun.learn.common.aop.annotation.Test)")
    public void useTest() {
    }

    @Before("useTest()")
    public void test() {
        log.info("Test方式注解切面生效...");
    }
}
