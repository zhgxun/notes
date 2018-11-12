package github.banana.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 统计每个入口进入的人数
 */
public class Entrance implements Runnable {

    /**
     * 记录所有门进入的总人数
     */
    private static Count count = new Count();

    /**
     * 保存每个门的实例
     */
    private static List<Entrance> entrances = new ArrayList<>();

    /**
     * 内部数量记录每个门口进入的人数数量
     */
    private int number = 0;

    /**
     * 标识当前对象ID
     */
    private final int id;

    /**
     * 线程安全方式同步当前对象的取消状态
     */
    private static volatile boolean cancel = false;

    /**
     * 构造当前对象, 并接受一个对象标识
     *
     * @param id 对象标识
     */
    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    /**
     * 将对象状态置为取消状态
     */
    public static void cancel() {
        cancel = true;
    }

    /**
     * 多线程方式记录进入的人数
     */
    @Override
    public void run() {
        // 当前对象未被取消时有用
        while (!cancel) {
            // 当前对象进入的人数数量
            synchronized (this) {
                ++number;
            }

            // 输出并统计更新进入公园的总人数
            System.out.println(this + " Total: " + count.increment());

            // 暂停一段时间
            // 控制线程的执行速度, 去除该段只是生成的数字更庞大
            // 因为入口的测试main开头控制时间为3秒, 3秒内如果不暂停可以产生大量的数字
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
                e.printStackTrace();
            }
        }
        // 当前对象被停止
        System.out.println("Stopping " + this);
    }

    /**
     * 当前对象即该公园门口进入的人数数量
     *
     * @return 人数
     */
    public synchronized int getValue() {
        return number;
    }

    /**
     * 对象的输出方式
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    /**
     * 进入公园的总人数, 该值由Count类递增来收集得到
     *
     * @return int
     */
    public static int getTotalCount() {
        return count.value();
    }

    /**
     * 统计暂存的每个门进入的数量, 正确时该人数与Count统计的总人数应该相等
     *
     * @return int
     */
    public static int sum() {
        int sum = 0;
        for (Entrance e : entrances) {
            sum += e.getValue();
        }

        return sum;
    }
}
