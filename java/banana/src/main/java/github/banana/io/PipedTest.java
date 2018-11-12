package github.banana.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * 输入输出测试
 */
public class PipedTest {

    public static void main(String[] args) throws IOException {
        // 准备输入输出流
        // 输出读入的流数据
        final PipedOutputStream outputStream = new PipedOutputStream();
        // 读入流数据
        final PipedInputStream inputStream = new PipedInputStream(outputStream);

        // 读入和输出不允许在同一个线程中执行, 读入和输出都是阻塞会导致死锁
        Thread in = new Thread(() -> {
            // 监听控制台输入
            Scanner scanner = new Scanner(System.in);
            while (true) {
                // 读取一行
                String input = scanner.nextLine();
                try {
                    outputStream.write(input.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread out = new Thread(() -> {
            try {
                // 缓存中最多读入的字符数, 超过会出现截断乱码
                byte[] buff = new byte[1024];
                int length;
                while ((length = inputStream.read(buff)) != -1) {
                    // 输出真实读取到的字符串
                    System.out.println("读取到的字节, 被转换为字符串: " + new String(buff, 0, length, Charset.defaultCharset()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        in.start();
        out.start();
    }
}
