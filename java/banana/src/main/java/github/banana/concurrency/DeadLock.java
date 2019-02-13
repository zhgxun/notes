package github.banana.concurrency;

/**
 * 死锁
 * 程序会一直阻塞运行中, 一时半会未必能观察得出来
 * <p>
 * 1. jps
 * 2. jstack pid 查看锁的状态
 * 对方一直持有锁, 一放一直在等待锁
 * 跟线程抢占存在关系, 看先后顺序, 如果抢占的顺序刚好允许程序顺利进行, 则不会产生死锁, 否则程序无法终止
 */
public class DeadLock {

    public static final Object LockA = new Object();
    public static final Object LockB = new Object();

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            System.out.println("线程A锁住LockA");
            synchronized (LockA) {
                System.out.println("线程A锁住LockB");
                // 这里有个顺序问题, 如果线程调度该处时LockB未被线程B锁住, 则不会产生死锁
                // 否则如果被线程B抢先锁住LockB, 则线程A再没机会获取到LockB, 则陷入死锁
                // 跟程序运行相关, 未必每次都发生
                synchronized (LockB) {
                    System.out.println("线程A休眠3秒中...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程A释放锁LockB");
            }
            System.out.println("线程A释放锁LockA");
        });

        Thread threadB = new Thread(() -> {
            System.out.println("线程B锁住LockB...");
            synchronized (LockB) {
                System.out.println("线程B锁住LockA...");
                synchronized (LockA) {
                    System.out.println("线程B执行完毕");
                }
                System.out.println("线程B释放锁LockA");
            }
            System.out.println("线程B释放锁LockB");
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
