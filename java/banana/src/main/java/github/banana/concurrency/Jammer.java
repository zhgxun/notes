package github.banana.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Jammer implements Runnable {

    /**
     * 涂抹完毕黄油的队列和即将推送的涂抹完毕果酱的队列
     */
    private ToastQueue butterQueue, jammerQueue;

    private Random random = new Random(47);

    /**
     * 构造两个已初始的涂抹完毕黄油队列和即将被消费的涂抹完毕果酱的队列
     *
     * @param butterQueue 涂抹完毕黄油的队列
     * @param jammerQueue 即将被下游消费的涂抹完毕果酱的队列
     */
    public Jammer(ToastQueue butterQueue, ToastQueue jammerQueue) {
        this.butterQueue = butterQueue;
        this.jammerQueue = jammerQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 随机暂停一段时间处理涂抹果酱
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));

                // 从涂抹完毕黄油的队列中取出一个土司
                Toast t = butterQueue.take();
                System.out.println("从涂抹黄油完成队列中获取一个已完成的土司来涂抹果酱: " + t + ", 并推送至下游等待消费者队列处理");

                // 涂抹果酱
                t.jam();

                // 推送至涂抹完毕果酱的队列等待消费者处理
                jammerQueue.put(t);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("涂抹果酱过程终止");
    }
}
