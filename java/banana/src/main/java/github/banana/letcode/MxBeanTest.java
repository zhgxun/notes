package github.banana.letcode;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 使用线程管理器MXBean
 *
 * java本身提供了一些关于线程, 内存, 垃圾回收和日志等管理的MXBean和一个ManagementFactory的静态工厂类,
 * 通过这些事先提供的类, 我们可以监控java进程的线程创建, 内存日志级别和垃圾回收等, 当然, 我们也可以通过
 * 创建我们自己的MXBean来实现我们想实现的一些功能
 *
 * Hello world!
 * 5: Monitor Ctrl-Break
 * 4: Signal Dispatcher
 * 3: Finalizer
 * 2: Reference Handler
 * 1: main
 */
public class MxBeanTest {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] threadIds = bean.getAllThreadIds();
        ThreadInfo[] infos = bean.getThreadInfo(threadIds);
        for (ThreadInfo info : infos) {
            System.out.println(info.getThreadId() + ": " + info.getThreadName());
        }
    }
}
