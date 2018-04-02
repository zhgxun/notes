package github.banana.concurrency;

/**
 * 线程的挂起和唤醒
 */
public class Blocker {

    /**
     * 尝试挂起线程
     */
    synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.println(Thread.currentThread() + " ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 唤起单个线程
     */
    synchronized void prod() {
        notify();
    }

    /**
     * 唤起所有线程
     */
    synchronized void prodAll() {
        notifyAll();
    }
}
