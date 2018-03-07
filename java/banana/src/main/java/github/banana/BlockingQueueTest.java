package github.banana;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列实例-搜索指定目录下文件中存在关键字的行
 */
public class BlockingQueueTest {

    // 文件队列长度
    private static final int FILE_QUEUE_SIZE = 10;
    // 当前可运行最大搜索线程数
    private static final int SEARCH_THREADS = 100;
    // 当前目录，作为队列处理完成的标识
    private static final File DUMMY = new File("");
    // 阻塞队列
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    /**
     * 搜索目录中存在关键字的行
     *
     * @param args args
     */
    public static void main(String args[]) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("输入目录名 (e.g. /opt/jdk1.8.0/src)");
            String directory = in.nextLine();
            System.out.println("输入要搜索的关键字 (e.g. volatile)");
            String keyword = in.nextLine();

            Runnable r = () -> {
                try {
                    enumerate(new File(directory));
                    // 最后存入空文件标识队列处理完毕
                    queue.put(DUMMY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(r).start();

            for (int i = 1; i <= SEARCH_THREADS; i++) {
                Runnable s = () -> {
                    try {
                        boolean done = false;
                        while (!done) {
                            File file = queue.take();
                            // 队列文件处理完毕，需要退出处理
                            if (file == DUMMY) {
                                queue.put(file);
                                done = true;
                            } else {
                                search(file, keyword);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
                new Thread(s).start();
            }
        }
    }

    /**
     * 递归枚举目录下文件名和目录名
     *
     * @param directory 目录名
     */
    private static void enumerate(File directory) {
        // 列出目录下的所有文件和目录
        File[] files = directory.listFiles();
        try {
            // 文件可能存在空指针异常
            if (files == null) {
                System.out.println("错误：该目录内无任何文件或目录");
                return;
            }
            for (File file : files) {
                // 如果是目录，递归列出文件名
                if (file.isDirectory()) {
                    enumerate(file);
                } else {
                    // 把文件名存入队列中
                    // 当Queue已经满了时，会进入等待，只要不被中断，就会插入数据到队列中。会阻塞，可以响应中断。
                    queue.put(file);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 搜索文件内是否包含关键字
     *
     * @param file    文件名
     * @param keyword 待搜索关键字
     */
    private static void search(File file, String keyword) {
        // 按UTF-8编码方式读取文件内容
        try (Scanner in = new Scanner(file, "UTF-8")) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                // 匹配文件内容是否包含关键字
                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s\n", file.getPath(), lineNumber, line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
