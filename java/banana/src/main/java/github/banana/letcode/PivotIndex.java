package github.banana.letcode;

public class PivotIndex {

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
        int[] nums = new int[]{-1, -1, -1, -1, -1, 0};
        System.out.println(pivotIndex(nums));
    }

    // 这个题一开始想到双指针, 但是没考虑到双指针所代表的真实规律
    // 我们可以这样想
    // 如果存在中心索引 index 则满足 中心索引两边的元素总和相同
    // leftSum + nums[index] + rightSum = totalSum;
    // leftSum = rightSum
    // 2 * leftSum + nums[index] = totalSum
    // 那问题就变得简单了, 从左边开始遍历首次遇到符合这个条件的下标就是中心索引, 否则不存在中心索引
    public static int pivotIndex(int[] nums) {
        int length = nums.length;

        // 数组总和
        int totalSum = 0;
        for (int left = 0; left < length; left++) {
            totalSum += nums[left];
        }

        // 左边累加和
        int leftSum = 0;
        for (int left = 0; left < length; left++) {
            // 比较已遍历元素是否已经满足中心索引条件
            if (leftSum * 2 + nums[left] == totalSum) {
                return left;
            }

            // 否则继续尝试累加
            leftSum += nums[left];
        }

        return -1;
    }
}
