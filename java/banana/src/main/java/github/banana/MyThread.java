package github.banana;

/**
 * Java程序入口就是由JVM启动main线程
 * 
 * main线程又可以启动其它线程
 * 
 * 当所有线程都运行结束时, JVM退出进程结束
 * 
 * 有一种线程的目的就是无限循环, 比如定时任务
 * 
 * 如果某个线程不结束, JVM线程就无法结束
 * 
 * 守护线程负责结束这类线程
 * 
 * 守护线程是为其它线程服务的线程
 * 
 * 所有非守护线程都结束完毕后, 虚拟机退出
 * 
 * 守护线程的特点是不能持有资源, 因为虚拟机退出后, 无法再释放资源等操作
 * 
 * 创建线程对象后立即调用setDaemon(true)即可创建守护线程
 * 
 * @author zhgxun
 *
 */
public class MyThread extends Thread {
    String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Hello, " + name + "!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Goodbye, " + name + "!");
    }
}
