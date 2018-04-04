package github.banana.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 涂抹黄油并推送至下游等待涂抹果酱处理
 */
public class Butterer implements Runnable {

    /**
     * 土司制作完成队列和涂抹黄油队列, 推送给下游涂抹果酱队列处理并完成消费
     */
    private ToastQueue dryQueue, buttererQueue;

    private Random random = new Random(47);

    /**
     * 构造方法接收两个已经初始化的土司制作完毕队列和即将推入涂抹完毕黄油的队列
     *
     * @param dryQueue      土司制作完毕的队列
     * @param buttererQueue 即将推送涂抹黄油完毕的队列
     */
    public Butterer(ToastQueue dryQueue, ToastQueue buttererQueue) {
        this.dryQueue = dryQueue;
        this.buttererQueue = buttererQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 随机暂停一段时间
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                // 从土司制作完成队列中一次获取一个土司来涂抹黄油
                Toast t = dryQueue.take();
                System.out.println("从土司制作完成队列中获取一个已完成的土司来涂抹黄油: " + t + ", 并推送至下游等待涂抹果酱队列处理");

                // 涂抹黄油
                t.butter();

                // 涂抹黄油完毕, 将其推送至下游涂抹果酱队列处理
                buttererQueue.put(t);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("涂抹黄油过程终止");
    }
}
