package github.banana.concurrency;

import java.util.concurrent.TimeUnit;

public class StackTest {

    private static final long count = 100000000L;
    private static volatile int value = 0;

    public static void main(String[] args) {
//        concurrent();
//        serial();
        final StackTest stackTest = new StackTest();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    stackTest.incr();
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Value: " + stackTest.value);
    }

    /**
     * 多增加一个线程来处理
     * <p>
     * 可以说, 并发只有在数据量增长到一定的程度时才有优势, 如果同步本身很快就处理完毕的事情, 使用多线程反而降低了程序的执行效率
     */
    private static void concurrent() {
        long start = System.currentTimeMillis();
        Thread t = new Thread(() -> {
            long a = 0;
            for (int i = 0; i < count; i++) {
                a += 5;
            }
        });
        t.start();

        long b = count;
        for (int i = 0; i < count; i++) {
            b--;
        }
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Concurrent: " + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 纯同步处理
     */
    private static void serial() {
        long start = System.currentTimeMillis();
        long a = 0;
        for (int i = 0; i < count; i++) {
            a += 5;
        }

        long b = count;
        for (int i = 0; i < count; i++) {
            b--;
        }
        System.out.println("Serial: " + (System.currentTimeMillis() - start) + "ms");
    }

    private void incr() {
        value++;
    }
}
