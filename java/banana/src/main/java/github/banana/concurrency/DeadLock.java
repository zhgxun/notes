package github.banana.concurrency;

/**
 * 死锁
 * 程序会一直阻塞运行中, 一时半会未必能观察得出来
 * <p>
 * 1. jps
 * 2. jstack pid 查看锁的状态
 * 对方一直持有锁, 一放一直在等待锁
 */
public class DeadLock {

    public static final Object LockA = new Object();
    public static final Object LockB = new Object();

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            synchronized (LockA) {
                synchronized (LockB) {
                    while (true) {
                    }
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (LockB) {
                synchronized (LockA) {
                    while (true) {
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
