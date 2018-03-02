package github.banana;

import java.util.Arrays;

/**
 * 账户管理
 */
public class Bank {
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
        if (accounts[from] < amount) {
            return;
        }

        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf("%10.2f from %d to %d\n", amount, from, to);
        accounts[to] += amount;
        System.out.printf("Total balance: %10.2f\n", getTotalBalance());
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
