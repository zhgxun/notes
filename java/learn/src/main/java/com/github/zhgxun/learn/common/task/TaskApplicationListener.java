package com.github.zhgxun.learn.common.task;

import com.github.zhgxun.learn.common.task.annotation.ScheduleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class TaskApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    // 任务启动监听类标识
    private static final String SPRING_TASK_CLASS = "spring.task.class";

    /**
     * 保存当前运行上下文
     */
    private ApplicationContext context;

    @Override
    @SuppressWarnings("unchecked")
    public void onApplicationEvent(ContextRefreshedEvent event) {
        context = event.getApplicationContext();
        String taskClass = System.getProperty(SPRING_TASK_CLASS);
        log.info("ScheduleTask spring task class: {}", taskClass);
        if (taskClass != null) {
            try {
                // 获取类字节码文件
                Class clazz = Class.forName(taskClass);
                // 尝试从内容上下文中获取已加载的类
                Object bean = context.getBean(clazz);
                // 获取类中的方法列表
                Method[] methods = bean.getClass().getDeclaredMethods();
                // 只提取有特定注解的方法来执行
                List<Method> schedules = Stream.of(methods)
                        .filter(method -> method.isAnnotationPresent(ScheduleTask.class))
                        .collect(Collectors.toList());
                // 任务仅支持一个注解方法
                if (schedules.size() != 1) {
                    throw new IllegalStateException("only one method should be annotated with @ScheduleTask, but found "
                            + "" + schedules.size());
                }
                // 获取待执行的目标方法
                Method method = schedules.get(0);
                log.info("start to run task class: {}, method: {}", taskClass, method.getName());
                // 执行目标方法
                method.invoke(bean);
            } catch (ClassNotFoundException e) {
                log.error("", e);
                throw new IllegalArgumentException("no class found with name:" + taskClass);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                // 执行完毕确保退出运行容器, 并将返回值交给执行环节, 比如控制台等
                System.exit(SpringApplication.exit(context));
            }
        }
    }
}
