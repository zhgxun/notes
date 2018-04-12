package github.banana.concurrency;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试可读可写管道
 */
public class Piped {

    public static void main(String[] args) {
        try {
            Sender sender = new Sender();
            Receiver receiver = new Receiver(sender);
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(sender);
            executorService.execute(receiver);
            TimeUnit.SECONDS.sleep(20);
            executorService.shutdownNow();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
