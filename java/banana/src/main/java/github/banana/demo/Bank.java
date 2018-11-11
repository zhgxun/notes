package github.banana.demo;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 账户管理
 */
public class Bank {
    // 重入锁（ReentrantLock）是一种递归无阻塞的同步机制
    // 一个可重入的互斥锁定 Lock，它具有与使用 synchronized 方法和语句所访问的隐式监视器锁定相同的
    // 一些基本行为和语义，但功能更强大。ReentrantLock 将由最近成功获得锁定，并且还没有释放该锁定的
    // 线程所拥有。当锁定没有被另一个线程所拥有时，调用 lock 的线程将成功获取该锁定并返回。如果当前线
    // 程已经拥有该锁定，此方法将立即返回。
    private Lock lock = new ReentrantLock();
    private final double[] accounts;

    public Bank(int n, double amount) {
        accounts = new double[n];
        Arrays.fill(accounts, amount);
    }

    /**
     * 转账
     *
     * @param from
     * @param to
     * @param amount
     */
    public void transfer(int from, int to, double amount) {
        lock.lock();
        try {
            if (accounts[from] < amount) {
                return;
            }

            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf("%10.2f from %d to %d\n", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Total balance: %10.2f\n", getTotalBalance());
        } finally {
            lock.unlock();
        }
    }

    /**
     * 账户余额
     *
     * @return
     */
    public double getTotalBalance() {
        double total = 0;
        for (double d: accounts) {
            total += d;
        }

        return total;
    }

    /**
     * 账户个数
     *
     * @return
     */
    public int size() {
        return accounts.length;
    }
}
