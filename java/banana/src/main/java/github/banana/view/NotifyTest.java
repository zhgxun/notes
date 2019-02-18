package github.banana.view;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * wait 和 notify 的搭配使用
 * <p>
 * {@link Object#wait()} 让当前线程进入等待, 直到被唤醒或被中断
 * {@link Object#wait(long)} 等待到超时时间
 * {@link Object#wait(long, int)}
 * <p>
 * {@link Object#notify()} 唤醒此对象当前监视器上的单个线程
 * {@link Object#notifyAll()} 唤醒此对象当前监视器上的所有线程
 * <p>
 * 如果某些条件没有被满足, 可以调用 wait 方法让当前线程暂停, 同时放弃已经获得的锁, 并且进入等待状态
 */
public class NotifyTest {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 5;
        // 正常的模型是单生产者, 多消费者
        System.out.println("生产者单线程启动消费者多线程启动");
        Producer producer1 = new Producer(queue, maxSize);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);
        Consumer consumer4 = new Consumer(queue);
        Consumer consumer5 = new Consumer(queue);
        Consumer consumer6 = new Consumer(queue);
        producer1.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
        consumer6.start();
    }
}

/**
 * 生产者模型
 */
class Producer extends Thread {

    // 生产者和消费者共享的队列
    private final Queue<Integer> queue;
    // 队列最大尺寸
    private int maxSize;

    Producer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                // 队列已满
                if (queue.size() == maxSize) {
                    System.out.println("队列已满, 挂起当前线程等待消费者消费数据");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 随机生成一个整数并添加到队列中
                Integer integer = new Random().nextInt(100);
                queue.offer(integer);

                // 通知消费者线程消费
                System.out.println(Thread.currentThread().getName() + ": 已生成一个数字, 通知消费者线程");
                queue.notifyAll();
            }
        }
    }
}

/**
 * 消费者模型
 */
class Consumer extends Thread {

    // 消费者和生产者共享的队列
    private final Queue<Integer> queue;

    Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            // 暂停让其它线程有机会获得锁, 就几乎很少出现生产者堵塞的情形了
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 锁粒度太重, 会锁住整个队列, 直到被挂起释放锁, 其它线程才有机会争抢
            // 优化可以改为读写锁, 每次获取一个数字就释放锁, 让其它线程去获得锁
            synchronized (queue) {
                while (queue.isEmpty()) {
                    // 队列为空则进入等待状态
                    try {
                        System.out.println("队列为空, 等待生产者生产数字");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 线程被唤醒时从队列中获取一个元素来消费
                System.out.println(Thread.currentThread().getName() + ": 消费者消费一个数字: " + queue.poll());
                // 同时消费完了需要通知所有等待的线程, 已经消费提供了足够的空间
                queue.notifyAll();
            }
        }
    }
}
