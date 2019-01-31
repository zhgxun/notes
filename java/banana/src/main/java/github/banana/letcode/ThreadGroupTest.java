package github.banana.letcode;

/**
 * 不断向上找父线程组, 否则只能获取当前线程组
 */
public class ThreadGroupTest {

    public static void main(String[] args) {
        System.out.println("Hello, world!");
        // 当前线程组
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while (group.getParent() != null) {
            group = group.getParent();
        }

        int num = group.activeCount();
        Thread[] threads = new Thread[num];
        group.enumerate(threads);
        for (int i = 0; i < num; i++) {
            System.out.println(i + ", " + threads[i].getName());
        }
    }
}
