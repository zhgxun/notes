package com.github.zhgxun.learn.notes.leetcode;

/**
 * 面试题10- II. 青蛙跳台阶问题
 * <p>
 * https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 * <p>
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */
public class NumWays {

    // 这个可能被描述的过于抽象了
    public int numWays(int n) {
        int a = 1, b = 1, sum;

        for (int i = 0; i < n; i++) {
            // 上一次台阶总统跳跃的总次数
            sum = (a + b) % 1000000007;
            // a 相当于记录上一次的计数
            a = b;
            b = sum;
        }
        return a;
    }
}
