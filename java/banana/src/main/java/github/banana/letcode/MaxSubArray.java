package github.banana.letcode;

/**
 * 53. 最大子序和
 * <p>
 * 给定一个整数数组 nums, 找到一个具有最大和的连续子数组(子数组最少包含一个元素), 返回其最大和
 */
public class MaxSubArray {

    public static void main(String[] args) {

    }

    public int maxSubArray(int[] nums) {
        /*
         * [-2,1,-3,4,-1,2,1,-5,4]
         */
        // 记录序列中的最大值
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            // 整数累加越大
            if (sum > 0) {
                sum += num;
            } else {
                // 负数变小了, 赋当前值
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
