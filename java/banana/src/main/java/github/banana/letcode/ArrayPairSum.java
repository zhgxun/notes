package github.banana.letcode;

import java.util.Arrays;

public class ArrayPairSum {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 3, 1, 0, 0, 6};
        System.out.println(arrayPairSum(nums));
    }

    public static int arrayPairSum(int[] nums) {
        // 边界和2个元素的处理
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        if (length == 2) {
            return Math.min(nums[0], nums[1]);
        }

        // 升序排序, 越小的跟越小的组合才能消除较小的数
        Arrays.sort(nums);

        int sum = 0;

        // 累加小值, 即下标为偶数的数值
        int left = 0;
        int right = length - 2;
        while (left <= right) {
            // 注意需要相等, 并且相等只能加一次
            if (left == right) {
                sum += nums[left];
            } else {
                sum += (nums[left] + nums[right]);
            }
            left += 2;
            right -= 2;
        }

        return sum;
    }
}
