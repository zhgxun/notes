package github.banana.view;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * {@link java.util.concurrent.ThreadPoolExecutor} 线程池核心参数
 * <p>
 * 1. corePoolSize 核心线程数
 * 核心线程会一直存活, 即是没有任务需要运行
 * 当线程数小于核心线程数时, 即是有线程空闲, 线程池也会优先创建新线程处理
 * 设置 allowCoreThreadTimeout=true 时, 核心线程会超时关闭, 默认false
 * <p>
 * 2. queueCapacity 任务队列容量(阻塞队列)
 * 当核心线程数达到最大时, 新任务会放在队列中排队等待处理
 * <p>
 * 3. maxPoolSize 最大线程数
 * 当线程数 > corePoolSize 且任务队列已满时, 线程池会创建新线程来处理任务
 * 当线程数 = maxPoolSize 且任务队列已满时, 线程池会拒绝处理任务而抛出异常
 * <p>
 * 4. keepAliveTime 线程空闲时间
 * 当线程空闲时间达到 keepAliveTime 时, 线程会退出, 直到线程数量 = corePoolSize
 * 如果 allowCoreThreadTimeout=true 则会直接退出直到线程数为0
 * <p>
 * 5. allowCoreThreadTimeout 允许核心线程超时
 * <p>
 * 6. rejectedExecutionHandler 任务拒绝处理器
 * 两种情况会拒绝任务
 * 当线程数已经达到 maxPoolSize 队列已满会拒绝新任务
 * 当线程池调用 shutdown 后, 会等待线程池里的任务执行完毕在 shutdown
 * 如果在调用 shutdown 和线程池真正 shutdown 之间提交任务会拒绝新任务
 * <p>
 * 7. 拒绝处理任务
 * 默认是 AbortPolicy 会抛出异常
 * AbortPolicy 丢弃任务, 抛运行时异常
 * CallerRunsPolicy 执行异常
 * DiscardPolicy 忽视, 什么都不发生
 * DiscardOldestPolicy 从队列中剔除最闲入队的任务
 * <p>
 * 可实现 RejectedExecutionHandler 接口自定义处理器
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(() -> System.out.println("Execute"));
        service.submit(() -> System.out.println("Submit"));
        service.shutdown();
    }
}
