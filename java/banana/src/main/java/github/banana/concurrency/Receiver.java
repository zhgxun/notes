package github.banana.concurrency;

import java.io.IOException;
import java.io.PipedReader;

/**
 * 可读管道
 */
public class Receiver implements Runnable {

    private PipedReader pipedReader;

    /**
     * 通过可写的管道创建一个可读的管道
     *
     * @param sender 已初始化后的可写的管道
     * @throws IOException 文件IO异常
     */
    public Receiver(Sender sender) throws IOException {
        pipedReader = new PipedReader(sender.getPipedWriter());
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Read: " + (char)pipedReader.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
