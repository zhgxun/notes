package github.banana.letcode;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target
 * 找出 nums 中的三个整数, 使得它们的和与 target 最接近, 返回这三个数的和
 * 假定每组输入只存在唯一答案
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        int[] a = {-1, 2, 1, -4};
        System.out.println(threeSumClosest(a, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        int length = nums.length;

        // 数组排序
        Arrays.sort(nums);

        // 记录相对较小的值, 最终相对较小的值就是跟目标值最接近的值
        int closeValue = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < length; i++) {
            int left = i + 1, right = length - 1;
            while (left < right) {
                // 当前三数之和, 取跟目标最接近者
                int current = nums[i] + nums[left] + nums[right];
                if (Math.abs(current - target) < Math.abs(closeValue - target)) {
                    closeValue = current;
                }

                // 当前值太大, 需要减小右值
                if (current > target) {
                    right--;
                } else if (current < target) {
                    left++;
                } else {
                    // 即三数之和已经跟目标值一样, 不需要再找了
                    return target;
                }
            }
        }

        // 否则就返回最接近的目标值
        return closeValue;
    }
}
