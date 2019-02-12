package github.banana.pool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建和销毁线程存在一定的开销, 因此涉及线程的操作相当的昂贵
 * 跟实例化对象一样, 需要时实例化, 并且能实例化一次的就不实例化多次, 这需要用一些方式解决, 确保对象只实例化一次, 高效利用
 * 核心线程数, 可以理解为长期驻留的线程数
 */
public class PoolTest {

    public static void main(String[] args) {

        // 1. 第一种, newCachedThreadPool 默认线程数不限制, 直到达到机器限制为准
        // 用途: 从来处理大量短时间工作任务的线程池, 会试图缓存线程并重用, 当无缓存线程可用时, 就会创建新的工作线程
        // 如果闲置线程超过60秒, 则被终止并移除缓存
        // 长时间闲置时, 这种线程池不会消耗什么资源
        // 其内部使用了
        /** {@link java.util.concurrent.SynchronousQueue} */
        // 作为工作队列
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            service.execute(() -> System.out.println("1. Executors.newCachedThreadPool 线程执行器"));
            // 阻塞等待
            service.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            service.shutdownNow();
        }

        // 2. 第二种, newFixedThreadPool 默认需要指定固定线程池数量
        // 重用指定书目的线程, 背后使用无界的工作队列, 任何时候左右有指定的个数个工作线程是活动的
        // 如果数量超过了活动队列书目, 将在工作队列中等待空闲线程出现
        // 如果有工作线程退出, 将会有新的工作线程被创建, 以补足指定的数目线程
        ExecutorService service1 = Executors.newFixedThreadPool(1);
        try {
            service1.submit(() -> System.out.println("2. Executors.newFixedThreadPool 线程执行器"));
            service1.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            service1.shutdownNow();
        }

        // 3. 第三种, newSingleThreadExecutor 线程数量只有1个
        // 工作线程数目被限制为1
        // 操作一个无界的工作队列, 它保证了所有的任务都是被顺序执行, 最多会有一个任务处于活动状态, 并且不允许使用者改动线程池实例
        // 因此可以避免其改变线程数目
        ExecutorService service2 = Executors.newSingleThreadExecutor();
        service2.submit(() -> System.out.println("3. Executors.newSingleThreadExecutor 线程执行器"));
        service2.shutdownNow();

        // 4. 第四种, newSingleThreadScheduledExecutor 单一工作线程
        // 可以进行定时或者周期性的工作调度
        // 跟
        /** {@link Executors#newScheduledThreadPool(int)} */
        // 的区别在于创建的属于单一线程或者多个线程
        // 返回值需要做任务调度
        ScheduledExecutorService service3 = Executors.newSingleThreadScheduledExecutor();
        service3.execute(() -> System.out.println("4. Executors.newSingleThreadScheduledExecutor 线程执行器"));
        service3.schedule(() -> System.out.println("调度任务, 延迟1秒钟执行一次"), 1, TimeUnit.SECONDS);
        service3.scheduleAtFixedRate(() -> System.out.println("固定频率任务, 每1秒钟执行一次"), 1, 1, TimeUnit.SECONDS);
        try {
            // 等待测试任务运行
            service3.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 终止线程, 包括固定频率的任务也将被终止掉
        service3.shutdownNow();

        // 5. 第五种, newWorkStealingPool
        // Jdk8开始提供, 内部构建
        /** {@link java.util.concurrent.ForkJoinPool} */
        // 利用 Work-Steal 算法来并行地处理任务, 不保证处理顺序
        ExecutorService service4 = Executors.newWorkStealingPool();
        service4.submit(() -> System.out.println("5. Executors.newWorkStealingPool 线程执行器 -- 1"));
        service4.submit(() -> System.out.println("5. Executors.newWorkStealingPool 线程执行器 -- 2"));
        service4.submit(() -> System.out.println("5. Executors.newWorkStealingPool 线程执行器 -- 3"));
        service4.submit(() -> System.out.println("5. Executors.newWorkStealingPool 线程执行器 -- 4"));
        service4.submit(() -> System.out.println("5. Executors.newWorkStealingPool 线程执行器 -- 5"));
        service4.submit(() -> System.out.println("5. Executors.newWorkStealingPool 线程执行器 -- 6"));
        service4.shutdownNow();
    }
}
