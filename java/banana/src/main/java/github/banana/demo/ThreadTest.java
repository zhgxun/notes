package github.banana.demo;

public class ThreadTest {

    public static void main(String[] args) {
        System.out.println("hello");

        // Exception in thread "main" java.lang.IllegalThreadStateException
//        Thread t = new Thread(() -> System.out.println("test"));
//        t.start();
//        t.start();

        // 获取线程组
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        while (group != null) {
            System.out.println("hihihi");
            topGroup = group;
            group = group.getParent();
        }
        int nowThreads = topGroup.activeCount();
        Thread[] lstThreads = new Thread[nowThreads];
        topGroup.enumerate(lstThreads);
        for (int i = 0; i < nowThreads; i++) {
            System.out.println("线程number：" + i + " = " + lstThreads[i].getName());
        }
    }
}
