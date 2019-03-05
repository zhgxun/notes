package github.banana.letcode;

import java.util.Arrays;

/**
 * 628. 三个数的最大乘积
 * <p>
 * 给定一个整型数组, 在数组中找出由三个数组成的最大乘积, 并输出这个乘积
 * <p>
 * 给定的整型数组长度范围是[3,104], 数组中所有的元素范围是[-1000, 1000]
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围
 * <p>
 * 除了暴力法其实还可以做排序, 冒泡找出最小的2个和最大的三个元素即可
 */
public class MaximumProduct {

    public static void main(String[] args) {
        int[] nums1 = {-4, -3, -2, -1, 60};
        System.out.println(new MaximumProduct().maximumProduct(nums1));
    }

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        // 只会存在两种情况的最大值都为正或者都是小于等于0的负数时最大值为后三个数字
        int a = nums[length - 1] * nums[length - 2] * nums[length - 3];
        // 存在正数时最大值也能为比较小的两个负数和最后一个正数
        int b = nums[0] * nums[1] * nums[length - 1];
        return a > b ? a : b;
    }
}
