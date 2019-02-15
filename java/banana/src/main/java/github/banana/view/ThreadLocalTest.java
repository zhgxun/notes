package github.banana.view;

import org.springframework.util.Assert;

/**
 * ThreadLocal 线程变量副本
 * <p>
 * synchronized 实现内存共享, {@link ThreadLocal} 为每个线程维护一个本地变量, 但线程内特别有用, 但是下线程池中需要小心使用
 * <p>
 * 采用空间换时间, 它用于线程间的数据隔离, 为每一个使用该变量的线程提供给一个副本, 每个线程都可以独立地改变自己的副本, 而不会和其它线程的
 * 副本冲突, 比如保存当前用户信息, 但是线程执行完毕后, 需要主动清空线程缓存数据, 清空方式推荐使用优雅的关闭方式
 * {@link AutoCloseable} 来实现 try resource finally 模式访问
 * <p>
 * {@link ThreadLocal} 类中维护一个 {@link java.util.Map}, 用于存储每一个线程的变量副本, {@link java.util.Map} 中元素的键为线程对象
 * 而值为对应线程的变量副本
 * {@link ThreadLocal} 在 Spring 中发挥着巨大作用, 在管理 {@link sun.net.httpserver.Request} 作用域中的 Bean, 事务管理, 任务调度,
 * AOP 等模块都出现了它的身影
 */
public class ThreadLocalTest {

    // 项目中使用时, 封装成工具, 其它方法注入调用即可
    private static ThreadLocal<Bean> local = new ThreadLocal<>();

    public static void main(String[] args) {
        String[] names = {"张三", "李四"};
        int i = 0;
        for (String name : names) {
            Bean bean = new Bean(name);
            String message = String.format("第 %s 个线程启动存储用户: %s 作为线程名称", i++, name);
            System.out.println(message);
            new Thread(new MyThread(local, bean), message).start();
        }
    }
}

/**
 * 普通数据类型
 */
class Bean {

    private String name;

    Bean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

/**
 * 测试启动一个线程
 */
class MyThread implements Runnable {

    private Bean bean;
    private ThreadLocal<Bean> local;

    MyThread(ThreadLocal<Bean> local, Bean bean) {
        Assert.notNull(bean, "Bean不能为空");
        this.local = local;
        this.bean = bean;
    }

    /**
     * 以线程启动的方式设置当前线程本地变量
     */
    @Override
    public void run() {
        local.set(bean);
        test();
    }

    private void test() {
        System.out.println(new TestBean(local).getExtName());
    }
}

/**
 * 测试从线程中获取本地副本变量
 * 需要保证操作在同一个线程中完成
 * 否则获取的线程就是其它线程的副本
 */
class TestBean {

    private ThreadLocal<Bean> local;

    TestBean(ThreadLocal<Bean> local) {
        this.local = local;
    }

    /**
     * 从线程中获取上一次设置的本地变量副本
     *
     * @return name
     */
    public String getExtName() {
        Bean bean = local.get();
        if (bean == null) {
            return Thread.currentThread().getName() + " 当前线程未存储该信息";
        }
        return String.format("从线程: %s 中获取用户: %s", Thread.currentThread().getName(), bean.getName());
    }
}
