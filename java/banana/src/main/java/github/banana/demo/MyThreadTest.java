package github.banana.demo;

import java.util.concurrent.CountDownLatch;

public class MyThreadTest {

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        System.out.println("正常运行...");

        Thread thread = new Thread(() -> {
            System.out.println("线程中运行");
            latch.countDown();
            throw new RuntimeException("运行时异常...");
        });
        // 可以捕获线程中的异常, 或者设置到一个全局map中
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("线程信息: " + t.getName());
            System.out.println("异常信息: " + e.getMessage());
        });
        thread.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行结束");
    }
}
