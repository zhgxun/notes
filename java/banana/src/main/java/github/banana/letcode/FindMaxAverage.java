package github.banana.letcode;

/**
 * 643. 子数组最大平均数 I
 * <p>
 * 给定 n 个整数, 找出平均数最大且长度为 k 的连续子数组, 并输出该最大平均数
 * <p>
 * 数组不可变, 暴力比较即可
 */
public class FindMaxAverage {

    public static void main(String[] args) {

    }

    public double findMaxAverage(int[] nums, int k) {
        int flagPartSum = 0;
        // 计算第一个连续子序列的和
        for (int i = 0; i < k; ++i) {
            flagPartSum += nums[i];
        }
        // 记录最大的连续子序列的和
        int maxPartSum = flagPartSum;
        // 指向子序列最后一个元素的指针
        int end = k;
        while (end < nums.length) {
            // 滑动窗口，记录下一个连续子序列的和
            flagPartSum = flagPartSum - nums[end - k] + nums[end];
            if (flagPartSum > maxPartSum) {
                // 比较，记录最大连续子序列的和
                maxPartSum = flagPartSum;
            }
            // 指针后移
            end++;
        }
        // 分母是小数才可返回小数
        return maxPartSum / (k + 0.0);
    }
}
