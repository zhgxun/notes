package github.banana.view;

import java.lang.reflect.Method;

/**
 * synchronized 和 {@link java.util.concurrent.locks.Lock}
 * <p>
 * 都是可重入锁, 同一个线程再次进入同步代码的时候可以使用自己已经获取到的锁
 * synchronized 是悲观锁机制, 独占锁
 * {@link java.util.concurrent.locks.ReentrantLock} 是每次不加锁而是假设没有冲突而去完成某项操作
 * 如果因为冲突失败就重试, 直到成功为止
 * <p>
 * {@link java.util.concurrent.locks.ReentrantLock} 适用场景
 * 1. 某个线程在等待一个锁的控制权的这段时间需要中断
 * 2. 需要分开处理一些 wait - notify, {@link java.util.concurrent.locks.ReentrantLock} 里面的 {@link java.util.concurrent.locks.Condition}
 * 能够控制 notify 哪个线程, 锁可以绑定多个条件
 * 3. 具有公平锁的功能, 每个到来的线程都将排队等候
 */
public class LockTest {

    public static void main(String[] args) {

        try {
            // 静态段会被初始化
            Class<?> s = Class.forName("github.banana.view.TestClassLoader");
            System.out.println(s.getSimpleName());
            // 找到方法并执行
            Method method = s.getMethod("exec");
            method.invoke(s);

            // 静态段不会被初始化
            Class<?> s1 = new MyClassLoader().loadClass("github.banana.view.TestClassLoader");
            System.out.println(s1.getSimpleName());
            Method method1 = s1.getMethod("exec");
            method1.invoke(s1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 自定义类加载器
 */
class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}

/**
 * 测试类加载器
 */
class TestClassLoader {

    static {
        System.out.println("测试类静态初始化段");
    }

    TestClassLoader() {
        System.out.println("测试类构造方法被调用");
    }

    /**
     * 需要找到方法才可以执行
     */
    public static void exec() {
        System.out.println("调用测试类方法执行");
    }
}
