package github.banana.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * {@link AtomicInteger} 理解
 * <p>
 * 是对int类型的一个封装, 提供了原子性的访问和更新操作, 原子性操作的实现是基于 CAS (compare and swap) 技术
 * <p>
 * CAS: 表征的是一系列操作的集合, 获取当前数值, 进行一些运算, 利用 CAS 指令试图进行更新
 * 如果当前数值未变, 代表没有其他线程进行并发修改, 则成功更新
 * 否则可能出现不同的选择, 要么进行重试, 要么就返回一个成功或失败的结果
 * <p>
 * 从 {@link AtomicInteger} 内部属性可以看出, 它依赖于 {@link jdk.internal.misc.Unsafe} 提供的一些底层能力, 进行底层操作
 * 以 volatile 的 value 字段, 记录数值, 以保证可见性
 * <p>
 * CAS 是 Java 并发中所谓 lock-free 机制的基础
 * <p>
 * 开发中未必会涉及 CAS 的实现层面, 但是理解其机制, 掌握如何在 Java 中运用该技术, 这也是并发编程面试的热点
 * <p>
 * CAS 更加底层的实现, 伊犁爱于 CPU 提供的特定指令, 具体根据体系结构的不同还是存在明显的区别
 * 比如, x86 CPU 提供 cmpxchg 指令, 而在精简指令集的体系架构中 通常是靠一对指令, 如 load and reserve 和 store conditional 实现的
 * 在大多数处理器上 CAS 都是非常轻量级的操作，这也是其优势所在
 * <p>
 * atomic 包提供了最常用的原子性数据类型, 甚至是引用, 数组等相关原子类型和更新操作工具, 是很多线程安全程序的首选
 * <p>
 * CAS 也并不是没有副作用, 试想, 其常用的失败重试机制, 隐含了一个假设, 即竞争情况是短暂的
 * 大多数应用场景中, 确实大部分重试只发生一次就获得了成功, 但是总有意外情况, 所以在有需要的时候, 还是要考虑限制自旋的次数, 以免过渡消耗 CPU
 * <p>
 * 另外一个就是著名的ABA问题, 这是通常只在 lock-free 算法下暴漏的问题
 * CAS 是在更新时比较前值, 如果对方只是恰好相同, 例如期间发生了 A -> B -> A 的更新, 仅仅判断数值是 A, 可能导致不合理的修改操作
 * 针对这种情况, Java 提供了 {@link java.util.concurrent.atomic.AtomicStampedReference} 工具类, 通过引用建立类似版本号 (stamp)
 * 的方式, 来保证 CAS 的正确性
 * <p>
 * 不过幸运的是, 大多数情况下, Java 开发者并不需要直接利用 CAS 代码去实现线程安全的容器等, 更多的是通过并发包等间接享受 lock-free 机制在
 * 扩展上的好处
 * <p>
 * AQS {@link java.util.concurrent.locks.AbstractQueuedSynchronizer}
 * 是 Java 并发包中, 实现各种同步结构和部分其它组成单元的基础
 * <p>
 * Doug Lea 曾经介绍过 AQS 的设计初衷
 * 从原理上, 一种同步结构往往是可以利用其它的结构实现的, 例如可以使用 {@link java.util.concurrent.Semaphore} 实现互斥锁
 * 但是对某种同步结构, 会导致复杂, 晦涩的实现逻辑, 所以, 他选择了将基础的同步相关操作抽象在 {@link java.util.concurrent.locks.AbstractQueuedSynchronizer}
 * 中, 利用 AQS 为我们构建同步结构提供范本
 * <p>
 * AQS 的内部数据和方法, 可以简单拆分为:
 * 1. 一个 volatile 的整数成员表征状态, 同时提供了 setState 和 getState 方法
 * 2. 一个先入先出(FIFO)的等待线程队列, 以实现多线程间竞争和等待, 这是 AQS 机制的核心之一
 * 3. 各种基于 CAS 的基础操作方法, 以及各种期望具体同步结构去实现的 acquire / release 方法
 * <p>
 * 利用 AQS 实现一个同步结构, 至少要实现两个基本类型的方法, 分别是 acquire 操作, 获取资源的独占权, 还有就是 release 操作, 释放对某个资源的独占
 * <p>
 * 比如 {@link java.util.concurrent.locks.ReentrantLock} 的实现
 * 内部有公平性 {@link java.util.concurrent.locks.ReentrantLock.FairSync} 和非公平性 {@link java.util.concurrent.locks.ReentrantLock.NonfairSync} 的实现
 * 默认是非公平性的同步实现
 * <p>
 * 公平的同步锁: 获取当前 AQS 的内部状态量 {@link AbstractQueuedSynchronizer#getState()} 为0时表示无其它线程独占, 则直接用 CAS 修改
 * 状态位, 并且不检查排队情况, 直接争抢, 成功则设置当前线程独占锁
 */
public class AtomicTest {

    public static void main(String[] args) {
    }
}
