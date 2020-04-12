package com.github.zhgxun.learn.notes.leetcode;

/**
 * 面试题17. 打印从1到最大的n位数
 * <p>
 * https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 */
public class PrintNumbers {

    // 其实就是计算n位数的最大值
    public int[] printNumbers(int n) {
        int max = n;
        int[] numbers = new int[max];
        for (int i = 1; i <= n; i++) {
            numbers[i - 1] = i;
        }

        return numbers;
    }
}
