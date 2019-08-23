package github.banana.letcode;

public class MaxSubArrayV2 {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    // -2,1,-3,4,-1,2,1,-5,4
    public static int maxSubArray(int[] nums) {
        int length = nums.length;
        // 最大子序和, 初始为第一个元素
        int maxSum = nums[0];
        // 当前连续子序列的和
        int currentSum = 0;
        for (int i = 0; i < length; i++) {
            // 如果当前连续的子序列和大于0则说明后面有机会变大
            if (currentSum > 0) {
                currentSum += nums[i];
            } else {
                // 否则一经递减为负数, 只会让后面的序列和变得更小, 故丢弃已处理过的数字, 从当前数字开始重新寻找
                currentSum = nums[i];
            }
            // 每次记录曾经历过的最大值, 取其中的一个最大值即可
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
