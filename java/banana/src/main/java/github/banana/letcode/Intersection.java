package github.banana.letcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 349. 两个数组的交集
 * <p>
 * 给定两个数组, 编写一个函数来计算它们的交集
 */
public class Intersection {

    public static void main(String[] args) {

    }

    // 看评论区, Set 更简单一些
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> target = new HashMap<>();

        int a = nums1.length;
        int b = nums2.length;

        // 长数组做一遍哈希法, 短数组来判断是否存在长数组中
        if (a >= b) {
            for (int n : nums1) {
                if (!map.containsKey(n)) {
                    map.put(n, 1);
                }
            }
            for (int n : nums2) {
                if (map.containsKey(n)) {
                    target.put(n, 1);
                }
            }
        } else {
            for (int n : nums2) {
                if (!map.containsKey(n)) {
                    map.put(n, 1);
                }
            }
            for (int n : nums1) {
                if (map.containsKey(n)) {
                    target.put(n, 1);
                }
            }
        }

        int[] result = new int[target.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : target.entrySet()) {
            result[i++] = entry.getKey();
        }

        return result;
    }
}
