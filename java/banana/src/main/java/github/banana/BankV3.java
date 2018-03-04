package github.banana;

import java.util.Arrays;

public class BankV3 {

    private final double[] accounts;

    BankV3(int n, double amount) {
        accounts = new double[n];
        Arrays.fill(accounts, amount);
    }

    public synchronized void transfer(int from, int to, double amount) {
        try {
            while (accounts[from] < amount) {
                wait();
            }

            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf("%10.2f from %d to %d\n", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Total balance: %10.2f\n", getTotalBalance());

            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized double getTotalBalance() {
        double sum = 0;
        for (double a: accounts) {
            sum += a;
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }
}
