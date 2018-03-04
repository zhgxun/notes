package github.banana;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankV2 {
    private final double[] accounts;
    private Lock lock;
    private Condition condition;

    BankV2(int n, double amount) {
        accounts = new double[n];
        Arrays.fill(accounts, amount);
        // 获取一个可重入锁
        lock = new ReentrantLock();
        // 获取一个锁的关联对象
        condition = lock.newCondition();
    }

    public void transfer(int from, int to, double amount) {
        lock.lock();
        try {
            while (accounts[from] < amount) {
                condition.await();
            }

            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf("%10.2f from %d to %d\n", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Total balance: %10.2f\n", getTotalBalance());

            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 所有账户余额
     *
     * @return double
     */
    public double getTotalBalance() {
        lock.lock();
        try {
            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return accounts.length;
    }
}
