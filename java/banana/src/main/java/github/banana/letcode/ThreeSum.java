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
        int[] a = {-1, 0, 1, 2, -1, -4, -1};
        System.out.println(threeSum(a));
        int[] b = {-2, 0, 0, 2, 2};
        System.out.println(threeSum(b));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        // {-1, 0, 1, 2, -1, -4, -1}
        List<List<Integer>> result = new ArrayList<>();

        int length = nums.length;
        if (length <= 2) {
            return result;
        }

        // {-4, -1, -1, -1, 0, 1, 2}
        // 1. 变为有序升序数组
        // 变为有序后, 其实观察就很容易发现, 但是排序的时间复杂度也是要考虑的
        Arrays.sort(nums);

        // 数组最左边是战斗力最弱的菜鸡, 最右边是战斗力最强的大神
        // 找的出发点是最左边的依次为C位, 寻找菜鸡和大神组合
        for (int i = 0; i < length; i++) {
            // 只有负数才有机会三数之和为0
            if (nums[i] > 0) {
                break;
            }

            // 去重, 因为它是C位, 故只用来组合一次, 由于本身有序, 故巧妙去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 同时从菜鸡和大神中找搭配队友
            // 左右指针完全寻找一次
            int left = i + 1, right = length - 1;
            while (left < right) {
                // {-4, -1, -1, -1, 0, 1, 2}
                int sum = nums[i] + nums[left] + nums[right];
                // 当前值太大, 战斗力太强, 需要降低大神的段位
                if (sum > 0) {
                    right--;
                    // 当前值太小, 菜鸡太多, 需要找稍微强一点的大神
                } else if (sum < 0) {
                    left++;
                } else {
                    // 找对了搭配队友, 存起来
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 此时已经找到搭配队友, 但要求是不重复的三元组, 即同类型的队友搭配一对即可, 其余不再搭配, 跳过即可
                    // 注意数组是升序, 将同级别的往后跳过
                    // 菜鸡跟菜鸡同级别
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 大神跟大神同级别
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // 同时更新前后指针
                    left++;
                    right--;
                }
            }
        }

        return result;
    }
}
