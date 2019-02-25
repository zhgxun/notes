package github.banana.letcode;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 */
public class SearchRange {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(Arrays.toString(searchRange(nums, 1)));
    }

    public static int[] searchRange(int[] nums, int target) {
        int left = -1, right = -1;
        if (nums == null || nums.length == 0) {
            return new int[]{left, right};
        }
        if (nums.length == 1 && nums[0] == target) {
            return new int[]{0, 0};
        }
        if (nums.length == 2) {
            if (nums[0] == nums[1] && nums[0] == target) {
                return new int[]{0, 1};
            }
            if (nums[0] == target) {
                return new int[]{0, 0};
            }
            if (nums[1] == target) {
                return new int[]{1, 1};
            }
            return new int[]{left, right};
        }

        int length = nums.length;
        left = left(nums, target, length);
        if (left >= 0) {
            right = right(nums, target, left, length - 1);
        }
        return new int[]{left, right};
    }

    private static int left(int[] nums, int target, int length) {
        int low = 0, high = length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (nums[middle] > target) {
                high = middle - 1;
            } else if (nums[middle] < target) {
                low = middle + 1;
            } else {
                // 左边到头或者左边往前一个值小于目标值, 则当前为目标最左位置
                if (middle == 0 || nums[middle - 1] < nums[middle]) {
                    return middle;
                } else {
                    high = middle - 1;
                }
            }
        }
        return -1;
    }

    private static int right(int[] nums, int target, int low, int high) {
        while (low <= high) {
            int middle = (low + high) / 2;
            if (nums[middle] > target) {
                high = middle - 1;
            } else if (nums[middle] < target) {
                low = middle + 1;
            } else {
                if (middle == high || nums[middle + 1] > target) {
                    return middle;
                } else {
                    low = middle + 1;
                }
            }
        }

        return -1;
    }
}
