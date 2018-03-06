package github.banana;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class BankV4 {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();

    private final double[] accounts;

    BankV4(int n, double amount) {
        accounts = new double[n];
        Arrays.fill(accounts, amount);
    }

    public synchronized void transfer(int from, int to, double amount) {
        writeLock.lock();
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
            writeLock.unlock();
        }
    }

    public synchronized double getTotalBalance() {
        readLock.lock();
        double sum = 0;
        for (double a: accounts) {
            sum += a;
        }
        readLock.unlock();
        return sum;
    }

    public int size() {
        return accounts.length;
    }
}
