package github.banana.letcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. 存在重复元素
 * <p>
 * 给定一个整数数组和一个整数 k
 * 判断数组中是否存在两个不同的索引 i 和 j, 使得 nums [i] = nums [j], 并且 i 和 j 的差的绝对值最大为 k
 */
public class ContainsNearbyDuplicate {

    public static void main(String[] args) {

    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            } else {
                map.put(nums[i], i);
            }
        }

        return false;
    }
}
