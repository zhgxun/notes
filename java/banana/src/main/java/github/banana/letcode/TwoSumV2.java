package github.banana.letcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumV2 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(nums, 9)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int length = numbers.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            Integer diff = target - numbers[i];
            // 顺序做hash肯定前面的下标要比后面的下标要小, 就是一遍hash法效率不高
            // 没有充分理由数组有序的特点, 二分查找一次只能固定一个元素故也用不上, 所以应该想到双指针解法
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i + 1};
            } else {
                map.put(numbers[i], i + 1);
            }
        }

        return new int[]{-1, -1};
    }

    public static int[] twoSumV2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int diff = target - (numbers[left] + numbers[right]);
            // 差值较大说明和小了增大小值即往右边移动
            if (diff > 0) {
                left++;
            } else if (diff < 0) {
                right--;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[]{-1, -1};
    }
}
