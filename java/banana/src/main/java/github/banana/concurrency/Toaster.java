package github.banana.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 土司制作生产者
 */
public class Toaster implements Runnable {

    private ToastQueue toastQueue;
    private int count = 0;
    private Random random = new Random(47);

    /**
     * 构造方法接收一个预先初始化的队列保存土司的制作属性
     *
     * @param tq 阻塞队列
     */
    public Toaster(ToastQueue tq) {
        toastQueue = tq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 随机暂停一个时间段模拟土司制作过程
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                // 构造土司并自增传入一个土司标识ID
                Toast t = new Toast(count++);
                System.out.println("制作完毕一个土司: " + t + ", 并推入制作完毕队列等待下游涂抹黄油处理");
                // 将该制作完毕的土司推入制作完毕队列中等待下游处理
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("土司制作过程终止");
    }
}
