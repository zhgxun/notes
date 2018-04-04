package github.banana.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 土司制作多线程阻塞队列方式实现
 */
public class ToastMatic {

    public static void main(String[] args) {
        // 初始化三个队列, 土司制作完毕队列, 涂抹黄油完毕队列和涂抹完毕果酱队列
        ToastQueue dryQueue = new ToastQueue(), butterQueue = new ToastQueue(), jamQueue = new ToastQueue();

        // 为每个制作过程启动一个新线程来处理
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            // 土司制作
            executorService.execute(new Toaster(dryQueue));

            // 土司涂抹黄油
            executorService.execute(new Butterer(dryQueue, butterQueue));

            // 土司涂抹果酱
            executorService.execute(new Jammer(butterQueue, jamQueue));

            // 最终消费者队列
            executorService.execute(new Eater(jamQueue));

            // 5秒钟时间执行线程
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 通知关闭所有线程
            executorService.shutdownNow();
        }
    }
}
