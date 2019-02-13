package github.banana.concurrency;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 并发工具类
 * java.util.concurrent 包及其子包
 * 1. 提供了比 synchronized 更加高级的各种同步结构
 * 比如
 * {@link java.util.concurrent.Semaphore} Java版本的信号量, 类似计数器
 * 提供 {@link Semaphore#acquire()} 和 {@link Semaphore#release()} 用于获取和释放计数器
 * {@link java.util.concurrent.CountDownLatch} 并发计数器, 允许一个或多个线程执行到某个点, 一般是执行完毕
 * {@link java.util.concurrent.CyclicBarrier} CyclicBarrier允许一组线程相互等待达到一个公共的障碍点
 * CyclicBarrier对于一组线程必须相互等待的场景很有用
 * 比如有一组线程, 都要往数据库里面写入操作, 只有当所有的线程都往数据库里面写入数据之后, 这些线程才能继续往下执行
 * <p>
 * CountDownLatch和CyclicBarrier都能够实现线程之间的等待, 只不过它们侧重点不同
 * CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后它才执行
 * 而CyclicBarrier一般用于一组线程互相等待至某个状态, 然后这一组线程再同时执行
 * 另外, CountDownLatch是不能够重用的, 而CyclicBarrier是可以重用的
 * <p>
 * 2. 各种线程安全的容器
 * {@link java.util.concurrent.ConcurrentHashMap} 并发安全的map容器
 * 等等
 * <p>
 * 3. 各种并发队列的实现
 * {@link java.util.concurrent.ArrayBlockingQueue} 阻塞的数组队列
 * {@link java.util.concurrent.PriorityBlockingQueue} 优先队列, 即是添加到队列中的元素有一定的顺序性
 * {@link java.util.concurrent.SynchronousQueue} 这个队列很特别
 * <p>
 * 4. 强大的 {@link Executors} 框架
 * 主要使用 {@link ExecutorService} 和
 * 支持周期性任务的 {@link java.util.concurrent.ScheduledExecutorService} 来实现
 * 并发线程池的管理
 * <p>
 * 多线程编程的目的:
 * 1. 提高程序的扩展能力, 达到业务对吞吐量的要求
 * 2. 协调线程间调度以及交互
 * 3. 线程间传递数据和状态
 * <p>
 * Map容器的选择
 * 1. 侧重于数据的存取不在乎顺序时选择 {@link java.util.concurrent.ConcurrentHashMap}
 * 2. 如果考虑顺序时选择 {@link java.util.concurrent.ConcurrentSkipListMap}
 * 如果我们对大量数据存在频繁的修改, 该数据结构可能存在优势
 * <p>
 * 不考虑线程并发安全时, 普通的Map存取我们选择 {@link java.util.HashMap} 数据结构,
 * 而如果存在顺序性要求时选择 {@link java.util.TreeMap} 结构, 但是并发的容器安全中, 并没有类似的实现, 因为实现相当复杂
 * 因为 {@link java.util.TreeMap} 基于复杂的红黑树
 * 为保证访问效率, 当我们插入或者删除节点时, 会移动节点进行平衡操作, 这导致在并发场景中难以进行合理粒度的同步
 * 然而 {@link java.util.concurrent.ConcurrentSkipListMap} 结构要相对简单的多, 通过层次结构提高访问速度, 虽然不够紧凑
 * 但空间使用有一定提高, 在增删元素时线程安全的开销要好很多
 * <p>
 * {@link java.util.concurrent.CopyOnWriteArrayList} CopyOnWrite 就是每次写操作都会拷贝原数组, 修改后替换原来的数组, 通过这种
 * 防御性的方式, 实现另类的线程安全
 * {@link java.util.concurrent.CopyOnWriteArraySet} 其实是通过包装 {@link java.util.concurrent.CopyOnWriteArrayList} 来实现的
 * 拷贝的方式其实就是 {@link Arrays#copyOf(Object[], int)} 方法实现的
 * 这种数据结构, 适用于读多写少的操作, 毕竟每次拷贝数组的开销是非常大的
 */
public class UtilTest {

    public static void main(String[] args) {
        // CyclicBarrier 实例测试
        new CyclicBarrierTest().eat();
    }
}

/**
 * 模拟示例:
 * 有几个同学约好一起去食堂吃饭, 各自都从各自的宿舍出发, 然后到宿舍楼下集合
 * 当所有的人都到了宿舍楼下之后, 再一起从宿舍楼下出发前往食堂吃饭
 * <p>
 * 该示例可以用各种不同的数据结构来实现, 哈哈
 */
class CyclicBarrierTest {
    // 同学数量
    private static final int NUMBER = 3;
    // 屏障, 等待所有线程执行完毕后, 统一执行的调用通知
    private static final CyclicBarrier barrier = new CyclicBarrier(NUMBER, () -> {
        System.out.println("我们都到达宿舍楼下了, 大家一起走吧");
        System.out.println("那我们就去西农小吃街那边吃新疆大盘价吧");
    });

    /**
     * 调度线程执行
     */
    void eat() {
        // 用线程池去执行
        ExecutorService service = Executors.newFixedThreadPool(3);
        // CyclicBarrier 使用样例
        System.out.println("第一波同学去吃饭...");
        String[] names = {"张三", "李四", "王五"};
        for (String name : names) {
            service.execute(new Walk(name));
        }
        // 模拟执行耗时, 因为其它线程有耗时, 不能太短, 否则其它线程还在等待中, 结果调用直接结束操作
        // 换作 service.wait(); 等待所有线程执行完毕也可以, 但是wait会存在一些问题
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 线程和屏障是可以重用的, 这又是另外三个同学
        System.out.println("第二波同学去吃饭...");
        String[] names1 = {"小红", "小明", "小芳"};
        Arrays.stream(names1).forEach(name -> service.execute(new Walk(name)));

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdownNow();
    }

    // CyclicBarrier 有两个构造函数
    // 参数parties指让多少个线程或者任务等待至barrier状态, 如果没有公共执行的内容, 默认为null
    // 参数barrierAction为当这些线程都达到barrier状态时会执行的内容

    class Walk implements Runnable {
        // 当前同学的名字
        private String name;

        Walk(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " 开始从宿舍出发");
            try {
                // 模拟每个同学走路2秒
                TimeUnit.SECONDS.sleep(2);
                // 每个同学都要等待所有同学到楼下再去吃饭
                barrier.await();
                System.out.println("大家都到齐了, " + name + " 开始从宿舍楼下出发");
                // 模拟走1秒
                TimeUnit.SECONDS.sleep(1);
                System.out.println(name + "到达新家大盘鸡店");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
