package github.banana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadUtil {

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (String name : Arrays.asList("Bob", "Alice", "Tom")) {
            threads.add(new MyThread(name));
        }
        // 创建新线程
        // 从Thread派生
        // 实现Runnable接口
        System.out.println("START");
        for (Thread t : threads) {
            // 启动, 让三个线程都执行
            // 一个线程对象只能调用一次start()
            // Java线程对象Thread的状态包括
            // New / Runnable / Blocked / Waiting / Timed Waiting / Terminated
            t.start();
        }
        for (Thread thread : threads) {
            try {
                // 等待该线程执行结束, 对于已经结束的线程直接返回
                // 通过对另一个线程对象调用join()方法可以等待其执行结束
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("END");
    }

}
