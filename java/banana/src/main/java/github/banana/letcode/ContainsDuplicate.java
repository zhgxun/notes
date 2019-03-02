package github.banana.letcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 217. 存在重复元素
 * <p>
 * 给定一个整数数组, 判断是否存在重复元素
 * <p>
 * 如果任何值在数组中出现至少两次, 函数返回 true
 * 如果数组中每个元素都不相同, 则返回 false
 */
public class ContainsDuplicate {

    public static void main(String[] args) {

    }

    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            }
            map.put(nums[i], 1);
        }
        return false;
    }
}
