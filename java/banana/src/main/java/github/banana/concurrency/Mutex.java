package github.banana.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock {

    private final Sync sync = new Sync();

    /**
     * 静态内部类自定义同步器
     */
    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 尝试获取锁
         *
         * @param arg 获取的次数
         * @return 获取锁是否成功
         */
        @Override
        protected boolean tryAcquire(int arg) {
            // 只有状态为0时未被占用, 否则无法获取锁
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁
         *
         * @param arg 释放次数
         * @return 是否释放成功
         */
        @Override
        protected boolean tryRelease(int arg) {
            // 线程本身没有持有锁无法释放, 锁异常
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            // 清空持有的线程和状态
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 是否处于占有状态
         *
         * @return 线程是否尺有锁
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.tryAcquire(1);
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
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
