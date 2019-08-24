package github.banana.letcode;

import java.util.HashSet;
import java.util.Set;

public class ContainsNearbyDuplicateV2 {

    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int length = nums.length;
        // 这个题可不是真的对初学者这么简单, set的想法还是不是那么好想
        // set 维护最多k个最相近的元素, 出现在里面说明相距不会大于k
        // 否则去除前面的旧有元素
        // 不着重关注下标, 而是用个数来控制
        // 想不到还真不好解
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);

            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
