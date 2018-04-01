package github.banana.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试进入公园门口的总人数是否正确
 */
public class Garden {

    public static void main(String[] args) {
        // 根据线程数量创建对应的线程来执行
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Entrance(i));
        }

        // 尝试等待线程同步数据, 否则主线程结束后其他线程被迫结束
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 设置对象的取消状态
        Entrance.cacen();

        // 关闭线程
        executorService.shutdown();

        // 未正常中断的线程
        try {
            if (!executorService.awaitTermination(250, TimeUnit.MILLISECONDS)) {
                System.out.println("Some tasks were not terminated");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 2种方法统计总人数是否一致
        System.out.println("Total: " + Entrance.getTotalCount());
        System.out.println("Sum of Entrances: " + Entrance.sum());
    }
}
