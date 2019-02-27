package github.banana.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 给定一个包含 n 个整数的数组 nums, 判断 nums 中是否存在三个元素 a,b,c, 使得 a + b + c = 0
 * 找出所有满足条件且不重复的三元组
 * <p>
 * 难点在于怎么去重, 借助排序后的规律, 相同的数字总会在一起, 那么往对应的方向移动到不重复的位置即可
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] a = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(a));
        int[] b = {-2, 0, 0, 2, 2};
        System.out.println(threeSum(b));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if (length <= 2) {
            return result;
        }

        // 1. 变为有序升序数组
        // 变为有序后, 其实观察就很容易发现, 但是排序的时间复杂度也是要考虑的
        Arrays.sort(nums);

        for (int i = 0; i < length; i++) {
            // 只有负数才有机会三数之和为0
            if (nums[i] > 0) {
                break;
            }

            // i 往后移动, 跟上一次相同时往后移动, 其实就是进入下一次循环
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = -nums[i];

            // 前后双指针查找
            int j = i + 1, k = length - 1;
            while (j < k) {
                int current = nums[j] + nums[k];
                // 当前值太大, 缩小末尾值
                if (current > target) {
                    k--;
                    // 当前值太小, 增加开始值
                } else if (current < target) {
                    j++;
                } else {
                    // 目标值
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    // 相同的情况
                    // 其实就是相邻的两个数字一致, 获取其中一个即可
                    // 获取的原则是往中间靠
                    // j 往后移动, 相同时移动到后一位
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    // k 往前移动, 相同时移动到前一位
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }

                    // 同时更新前后指针
                    j++;
                    k--;
                }
            }
        }

        return result;
    }
}
