package github.banana.letcode;

/**
 * 674. 最长连续递增序列
 * <p>
 * 给定一个未经排序的整数数组, 找到最长且连续的的递增序列
 * 连续递增的数字就可以不是序列哈哈哈哈
 */
public class FindLengthOfLCIS {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 2, 3, 4, 5};
        System.out.println(new FindLengthOfLCIS().findLengthOfLCIS(nums));
        int[] nums1 = {2, 2, 2, 2, 2, 2};
        System.out.println(new FindLengthOfLCIS().findLengthOfLCIS(nums1));
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int start = 0;
        int sum = 1;
        for (int i = 1; i < length; i++) {
            // 不递增时更新下标, 也类似滑动窗口
            if (nums[i] <= nums[i - 1]) {
                if (i - start > sum) {
                    sum = i - start;
                }
                start = i;
            }
        }
        // 全部递增时用
        if (length - start > sum) {
            sum = length - start;
        }

        return sum;
    }
}
