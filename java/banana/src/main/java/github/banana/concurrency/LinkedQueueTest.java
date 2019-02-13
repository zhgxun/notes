package github.banana.concurrency;

import java.util.concurrent.Executors;

/**
 * 习惯上, 我们把java.util.concurrent下的工具都叫做并发工具, 但严格来说, 只有Concurrent前缀的工具才算并发容器
 * <p>
 * 1. Concurrent 类型基于 lock-free , 在常见的多线程访问场景中, 一般可以提供较高的吞吐量
 * 2. {@link java.util.concurrent.LinkedBlockingQueue} 内部则是基于锁, 并提供了 {@link java.util.concurrent.BlockingQueue} 的等待性方法
 * 3. 当然, 并发包下的容器(Queue, List, Set), Map从命名上可以分三类: Concurrent*, CopyOnWrite*, Blocking, 的确都是线程安全的容器
 * 4. 但是也有区别, Concurrent*类型没有类似 CopyOnWrite 之类容器相对较重的修改开销
 * 5. 因此 Concurrent 往往提供较低的遍历一致性, 即是所谓的弱一致性, 例如当迭代器遍历时, 如果容器发生修改, 迭代器依然可以继续进行遍历
 * 6. 与弱一致性对应的, 就是同步容器常见的行为"fail-fast", 检测到容器在遍历过程中发生改变,
 * 抛出 {@link java.util.ConcurrentModificationException} 被修改异常, 不再继续遍历
 * 7. 弱一致性的另外一个体现是size等操作准确性有限, 不保证完全正确
 * 8. 读取性能具有一定的不确定性
 * <p>
 * 队列是非常重要的数据结构, 开发中很多线程间数据传递都要依赖于它
 * {@link github.banana.zk.Executor} 框架提供的各种线程池, 同样无法离开队列
 * <p>
 * 双端队列
 * {@link java.util.concurrent.LinkedBlockingDeque}
 * {@link java.util.concurrent.ConcurrentLinkedDeque}
 * <p>
 * 从行为特征来看, 绝大部分 {@link java.util.Queue} 都实现了 {@link java.util.concurrent.BlockingQueue} 接口
 * 在常规队列操作基础上, Blocking 意味着其提供了特定的等待性操作, 获取时(take)等待元素进队, 或者插入(put)时等待队列出现空位
 * <p>
 * 是否有界(Bounded)无界(Unbounded), 这个区别往往影响在应用开发中的选择
 * {@link java.util.concurrent.ArrayBlockingQueue} 是最典型的有界队列, 其内部以 final 的数组保存数据, 数组的大小就决定了队列的边界
 * 所以我们在创建 {@link java.util.concurrent.ArrayBlockingQueue} 时, 需要指定容量
 * <p>
 * {@link java.util.concurrent.LinkedBlockingDeque} 容易被误解为无边界, 但其实其行为和内部代码都是基于有界的逻辑实现的, 只不过如果
 * 我们没有在创建队列时指定队列容量, 那么其实容量就自动设置为 {@link Integer#MAX_VALUE} 即整数最大值, 可以认为该数值是无界的
 * <p>
 * {@link java.util.concurrent.SynchronousQueue} 这是一个非常奇葩的队列实现, 每个删除操作都要等待插入操作, 反之每个插入操作也都要等待删除动作
 * 那么这个队列的容量是多少呢? 其实不是的, 内部容量是0
 * <p>
 * {@link java.util.concurrent.PriorityBlockingQueue} 是无边界队列, 严格意义上无边界队列受资源限制
 * <p>
 * {@link java.util.concurrent.DelayQueue} 和 {@link java.util.concurrent.LinkedTransferQueue} 同样是无边界队列
 * 对于无边界队列, 有一个自然结果, 就是put操作永远不发生阻塞操作, 在系统资源允许的范围内
 * <p>
 * 一般来说, {@link java.util.concurrent.BlockingQueue} 都是基于锁实现的
 * <p>
 * {@link java.util.concurrent.ConcurrentLinkedQueue} 是基于无锁 CAS 实现, 不需要在每个操作时使用锁, 扩展性表现比较优异
 * {@link java.util.concurrent.SynchronousQueue} 从Java 6 开始, 用 CAS 替换掉原本基于锁的逻辑, 同步开销比较小, 它是
 * {@link Executors#newCachedThreadPool()} 内部的默认队列实现
 * <p>
 * 总结:
 * 1. 边界, {@link java.util.concurrent.ArrayBlockingQueue} 是有明确的容量限制, 而
 * {@link java.util.concurrent.LinkedBlockingQueue} 取决于我们是否创建时指定, 默认无界
 * {@link java.util.concurrent.SynchronousQueue} 干脆不缓存任何元素
 * 2. 空间, {@link java.util.concurrent.ArrayBlockingQueue} 要比 {@link java.util.concurrent.LinkedBlockingQueue}
 * 紧凑, 因为不需要创建所谓的节点, 但是其初始化分配阶段就需要一段连续的内存空间, 所以初始内存需求更大
 * 3. 吞吐量, {@link java.util.concurrent.LinkedBlockingQueue} 一般要优于 {@link java.util.concurrent.ArrayBlockingQueue}
 * 因为实现了更细粒度的锁操作
 * 4. 性能, {@link java.util.concurrent.ArrayBlockingQueue} 实现比较简单, 性能更好预测, 属于表现稳定的"选手"
 * 5. 协调, {@link java.util.concurrent.CountDownLatch} 和 {@link java.util.concurrent.SynchronousQueue} 都能协调线程间
 * 数据传输, 但后者代码更加规范
 * 6. 可能令人意外的是, 很多时候 {@link java.util.concurrent.SynchronousQueue} 的性能表现，往往大大超过其他实现, 尤其是在队列元素较小的场景
 * <p>
 * 醉令人惊奇的, 其实还是借助 Blocking 来实现, 本身是阻塞的, 避免了自行实现时轮训和等待等检查操作, 大大方便了该类型应用
 */
public class LinkedQueueTest {

    public static void main(String[] args) {

    }
}
