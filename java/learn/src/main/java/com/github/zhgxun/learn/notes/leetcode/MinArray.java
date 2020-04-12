package com.github.zhgxun.learn.notes.leetcode;

/**
 * 面试题11. 旋转数组的最小数字
 * <p>
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 */
public class MinArray {

    // 旋转数组的最大特点是
    // 左排序数组的任意元素 >= 右排序数组任意元素
    public int minArrayV2(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            // 因此当 中点的值大于右边的值时 m 一定在左排序数组中, 即需要快速进入右排序数组区间
            if (numbers[middle] > numbers[right]) {
                left = middle + 1;
            } else if (numbers[middle] < numbers[right]) {
                // 当中点元素的值小于右排序数组时说明此时 m 在右排序数组中, 目标值在 [left, middle] 区间中
                // 此时注意不要减少中点的值
                right = middle - 1;
            } else {
                // 但是左右值相等时是无法区分在哪个排序数组中的
                // 此时逐步缩小中点的值, 不过证明很繁琐
                right--;
            }
        }

        return numbers[left];
    }

    public int minArray(int[] numbers) {
        int len = numbers.length;

        // 找到第一个相邻的差异即可
        // 1,2,3,3,4,5,5
        // 全部递增则直接返回首元素不能取相等
        if (numbers[0] < numbers[len - 1]) {
            return numbers[0];
        }

        // 5,5,4,3,3,2,1
        // 全部递减无法判断

        // 3,4,5,5,1,2,3
        for (int i = 0; i < len - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return numbers[i + 1];
            }
        }

        // 全部递减
        return numbers[len - 1];
    }

    public static void main(String[] args) {
        int[] numbers = {3, 4, 5, 1, 2};
    }
}
