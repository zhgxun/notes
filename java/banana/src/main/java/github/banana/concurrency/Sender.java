package github.banana.concurrency;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 可写管道
 */
public class Sender implements Runnable {

    private Random random = new Random(47);
    private PipedWriter pipedWriter = new PipedWriter();

    /**
     * 获取当前可读写的管道
     *
     * @return {@link PipedWriter}
     */
    public PipedWriter getPipedWriter() {
        return this.pipedWriter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (char c = 'A'; c <= 'z'; c++) {
                    pipedWriter.write(c);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
