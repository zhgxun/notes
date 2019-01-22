package com.github.zhgxun.learn.common.task;

import com.github.zhgxun.learn.common.task.annotation.ScheduleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 不自动加入容器, 用于区分是否属于任务启动, 否则放入容器中, Spring 无法选择性执行
 * 需要根据特殊参数在启动时注入
 * 该监听器本身不能访问容器变量, 如果需要访问, 需要从上下文中获取对象实例后方可继续访问实例信息
 * 如果其它类中启动了多线程, 是无法接管异常抛出的, 需要子线程中正确处理退出操作
 * 该监听器最好不用直接做线程操作, 子类的实现不干预
 * 容器刷新事件可用, 启动事件不支持该触发
 */
@Slf4j
public class TaskApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * 任务启动监听类标识, 启动时注入
     * 即是 java -Dspring.task.class=com.github.zhgxun.learn.task.TestTask -jar learn.jar
     */
    private static final String SPRING_TASK_CLASS = "spring.task.class";

    /**
     * 支持该注解的方法个数, 目前仅一个
     * 可以理解为控制台一次执行一个类, 依赖的任务应该通过其它方式控制依赖
     */
    private static final int SUPPORT_METHOD_COUNT = 1;

    /**
     * 保存当前容器运行上下文
     */
    private ApplicationContext context;

    /**
     * 监听容器刷新事件
     *
     * @param event 容器刷新事件
     */
    @Override
    @SuppressWarnings("unchecked")
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 不存在时可能为正常的容器启动运行, 无需关心
        String taskClass = System.getProperty(SPRING_TASK_CLASS);
        log.info("ScheduleTask spring task Class: {}", taskClass);
        if (taskClass != null) {
            context = event.getApplicationContext();
            try {
                // 获取类字节码文件
                Class clazz = findClass(taskClass);

                // 尝试从内容上下文中获取已加载的目标类对象实例, 这个类实例是已经加载到容器内的对象实例, 即可以获取类的信息
                Object object = context.getBean(clazz);

                Method method = findMethod(object);

                log.info("start to run task Class: {}, Method: {}", taskClass, method.getName());
                invoke(method, object);
            } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                // 需要确保容器正常出发停止事件, 否则容器会僵尸卡死
                shutdown();
            }
        }
    }

    /**
     * 根据class路径名称查找类文件
     *
     * @param clazz 类名称
     * @return 类对象
     * @throws ClassNotFoundException ClassNotFoundException
     */
    private Class findClass(String clazz) throws ClassNotFoundException {
        return Class.forName(clazz);
    }

    /**
     * 获取目标对象中符合条件的方法
     *
     * @param object 目标对象实例
     * @return 符合条件的方法
     */
    private Method findMethod(Object object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        List<Method> schedules = Stream.of(methods)
                .filter(method -> method.isAnnotationPresent(ScheduleTask.class))
                .collect(Collectors.toList());
        if (schedules.size() != SUPPORT_METHOD_COUNT) {
            throw new IllegalStateException("only one method should be annotated with @ScheduleTask, but found "
                    + schedules.size());
        }
        return schedules.get(0);
    }

    /**
     * 执行目标对象方法
     *
     * @param method 目标方法
     * @param object 目标对象实例
     * @throws IllegalAccessException    IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     */
    private void invoke(Method method, Object object) throws IllegalAccessException, InvocationTargetException {
        method.invoke(object);
    }

    /**
     * 执行完毕退出运行容器, 并将返回值交给执行环节, 比如控制台等
     */
    private void shutdown() {
        log.info("shutdown ...");
        System.exit(SpringApplication.exit(context));
    }
}
