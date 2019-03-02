package github.banana.letcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 220. 存在重复元素 III
 * <p>
 * 给定一个整数数组, 判断数组中是否有两个不同的索引 i 和 j,
 * 使得 nums [i] 和 nums [j] 的差的绝对值最大为 t, 并且 i 和 j 之间的差的绝对值最大为 ķ
 * <p>
 * 注意溢出问题, 转化为 long 类型避开溢出
 */
public class ContainsNearbyAlmostDuplicate {

    public static void main(String[] args) {

    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Map<Long, Long> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (Map.Entry<Long, Long> entry : map.entrySet()) {
                if (Math.abs(entry.getKey() - (long) nums[i]) <= (long) t && Math.abs(entry.getValue() - (long) i) <= (long) k) {
                    return true;
                }
            }
            // 先判断后存储下标就不存在冲突
            map.put((long) nums[i], (long) i);
        }

        return false;
    }
}
