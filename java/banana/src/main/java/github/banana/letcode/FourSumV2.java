package github.banana.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSumV2 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        System.out.println(fourSum(nums, 0));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if (length < 4) {
            return result;
        }

        Arrays.sort(nums);

        // 固定第一个数
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 因为是升序数组, 故当前和后三个之和已经大于target则结束循环
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 后三个之和小于目标值则继续
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }

            // 固定第二个数
            for (int j = i + 1; j < length - 2; j++) {
                // 去重
                if (j > 0 && (nums[j] == nums[j - 1]) && (j - 1 != i)) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }

                // 此时已转化为三数之和找目标值, 前后指针法
                int left = j + 1;
                int right = length - 1;
                while (left < right) {
                    int diff = target - nums[i] - nums[j] - nums[left] - nums[right];
                    if (diff > 0) {
                        left++;
                    } else if (diff < 0) {
                        right--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 左右两边重复的值去除
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
