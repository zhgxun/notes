package com.github.zhgxun.learn.notes.leetcode;

/**
 * 面试题10- I. 斐波那契数列
 * <p>
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 */
public class Fib {

    public static void main(String[] args) {
        System.out.println(fib(2));
        System.out.println(fib(5));
    }

    // 注意取模
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }
}
