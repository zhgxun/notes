package github.banana.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给一个整数数组和一个目标值, 返回数组中两个元素相加后等于目标值的原数组元素下标
 * 
 * 比如Given nums = [2, 7, 11, 15], target = 9
 * 
 * Because nums[0] + nums[1] = 2 + 7= 9
 * 
 * return [0, 1]
 * 
 * @author zhgxun
 *
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int[] res;
        res = twoSum(nums, 9);
        System.out.println(Arrays.toString(res));

        res = twoSumV2(nums, 9);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 双层遍历
     * 
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // 判断值是否相等
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    /**
     * 使用map
     * 
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumV2(int[] nums, int target) {
        // 存储元素键为数组元素值, 元素值为数组元素下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 计算的目标值
            int goal = target - nums[i];
            // 看该值是否存在于hash表中
            if (map.containsKey(goal)) {
                return new int[] { map.get(goal), i };
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
