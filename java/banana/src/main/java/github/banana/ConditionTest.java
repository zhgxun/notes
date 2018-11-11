package github.banana;

public class ConditionTest {

    private volatile static boolean isRunning = true;

    public static void main(String[] args) {
        System.out.println("测试可见性");

        Thread t = new Thread(() -> {
            while (isRunning) {
                System.out.println("线程内运行");
            }
        });
        t.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 修改可见性
        System.out.println("修改可见性, 线程同步时间内线程其实依旧运行");
        isRunning = false;
        System.out.println("线程应该停止继续运行, 未必会完全停止");
    }
}
