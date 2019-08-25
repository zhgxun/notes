package github.banana.letcode;

public class FindMaxAverageV2 {

    public double findMaxAverage(int[] nums, int k) {
        int length = nums.length;

        // 记录最大连续k个子数组和
        int maxSum = 0;

        // 初始化为前k个和
        for (int i = 0; i < k; i++) {
            maxSum += nums[i];
        }

        int temp = maxSum;
        // k是长度, 下标为 i+k-1
        for (int i = 1; i + k - 1 < length; i++) {
            // 就是每次累计当前连续子数组的和
            // 在上一次的基础上增加当前元素, 同时去除最前面的元素, 因为此时超出k个了
            temp = temp + nums[i + k - 1] - nums[i - 1];
            // 比较是连续和否变大
            if (temp > maxSum) {
                maxSum = temp;
            }
        }

        return maxSum * 1.0 / k;
    }
}
