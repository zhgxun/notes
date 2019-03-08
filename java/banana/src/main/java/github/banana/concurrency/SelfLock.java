package github.banana.concurrency;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class SelfLock implements Lock {
    // 原子性的记录当前线程
    private AtomicReference<Thread> thread = new AtomicReference<>();
    // 等待线程队列
    private LinkedBlockingQueue<Thread> queue = new LinkedBlockingQueue<>();

    @Override
    public void lock() {
        // 让当前线程抢占锁
        while (!thread.compareAndSet(null, Thread.currentThread())) {
            // 未抢占成功的线程进入等待队列
            queue.add(Thread.currentThread());
            // 使其进入等待状态
            LockSupport.park();
            // 出队
            queue.remove(Thread.currentThread());
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        if (thread.compareAndSet(Thread.currentThread(), null)) {
            // 释放锁成功后通知争抢锁
            // 这样是非公平的, 公平锁需要FIFO的队列来实现
            Object[] threads = queue.toArray();
            for (Object t : threads) {
                LockSupport.unpark((Thread) t);
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
