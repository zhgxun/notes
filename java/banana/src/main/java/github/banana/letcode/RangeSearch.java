package github.banana.letcode;

import java.util.Arrays;

public class RangeSearch {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(nums, 10)));
    }

    public static int[] searchRange(int[] nums, int target) {
        return new int[]{search(nums, target, true), search(nums, target, false)};
    }

    /**
     * 搜索排序数组中的目标值的最左下标和最右下标
     *
     * @param nums   升序排序数组
     * @param target 目标值
     * @param min    找最左下标还是最右下边
     * @return 索引
     */
    public static int search(int[] nums, int target, boolean min) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                // 此时相等, 但是未必是第一个元素或者最后一个元素
                // 找最小位置, 左边找
                if (min) {
                    // 注意保证当前不是首元素
                    if (middle > 0 && nums[middle - 1] == nums[middle]) {
                        right = middle - 1;
                    } else {
                        return middle;
                    }
                } else {
                    // 找最大位置, 右边找
                    if (middle < right && nums[middle] == nums[middle + 1]) {
                        left = middle + 1;
                    } else {
                        return middle;
                    }
                }
            }
        }

        return -1;
    }
}
