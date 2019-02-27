package github.banana.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 */
public class FourSum {

    public static void main(String[] args) {
        int[] a = {1, 0, -1, 0, -2, 2};
        System.out.println(fourSum(a, 0));
        int[] b = {0, 0, 0, 0};
        System.out.println(fourSum(b, 0));
        int[] c = {0, 4, -5, 2, -2, 4, 2, -1, 4};
        System.out.println(fourSum(c, 12));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if (length < 4) {
            return result;
        }

        Arrays.sort(nums);

        // 思路就是固定2个数, 往后找后两个数
        for (int i = 0; i < length - 3; i++) {

            if (i > 0 && (nums[i] == nums[i - 1])) {
                continue;
            }

            // 固定nums[i], 并且nums[i]必须为负数或者0

            // 将四数之和转换为三数之和
            for (int j = i + 1; j < length - 2; j++) {
                // 注意这个边界条件
                if (j > 0 && (nums[j] == nums[j - 1]) && (j - 1 != i)) {
                    continue;
                }

                // 固定第二个数

                // 将三数之和转变为两数之和
                int value = target - nums[i] - nums[j];

                // 接下来继续找后两个数
                int left = j + 1, right = length - 1;
                while (left < right) {
                    // 注意理解比较的范围趋势
                    int current = value - nums[left] - nums[right];
                    if (current > 0) {
                        left++;
                    } else if (current < 0) {
                        right--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

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
