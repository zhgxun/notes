package github.banana.concurrency;

import java.util.Random;

/**
 * 线程安全的方式统计进入公园门口的总人数
 */
public class Count {

    /**
     * 记录公园入口进入的总人数
     */
    private int count = 0;

    /**
     * 随机产生一个布尔值, 留出时间让数据进行同步
     */
    private Random random = new Random(47);

    /**
     * 统计公园入口进入的总人数
     *
     * @return int
     */
    public synchronized int increment() {
        // 重做一次计数增加失败的可能性
        // 只是人为进行干扰而已
//        int temp = count;
//        if (random.nextBoolean()) {
//            Thread.yield();
//        }
//        return (count = ++temp);
        return ++count;
    }

    /**
     * 线程安全的方式获取进入公园门口的总人数
     *
     * @return int
     */
    public synchronized int value() {
        return count;
    }
}
