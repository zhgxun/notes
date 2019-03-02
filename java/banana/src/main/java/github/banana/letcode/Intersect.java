package github.banana.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 350. 两个数组的交集 II
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集
 */
public class Intersect {

    public static void main(String[] args) {
        int[] a = {-2147483648, 1, 2, 3};
        int[] b = {1, -2147483648, -2147483648};
        System.out.println(Arrays.toString(new Intersect().intersect(a, b)));
    }

    // 还是哈希解法
    public int[] intersect1(int[] nums1, int[] nums2) {
        // 注意整形溢出, 最简单的办法还是将范围扩大到 long 类型
        Map<Long, Integer> mapA = new HashMap<>();
        Map<Long, Integer> mapB = new HashMap<>();
        Map<Long, Integer> target = new HashMap<>();

        int a = nums1.length;
        int b = nums2.length;

        // 两个数组都需要做一遍哈希, 并记录数字出现的次数
        for (int i = 0; i < a; i++) {
            long dest = nums1[i];
            if (mapA.containsKey(dest)) {
                mapA.put(dest, mapA.get(dest) + 1);
            } else {
                mapA.put(dest, 1);
            }
        }
        for (int i = 0; i < b; i++) {
            long dest = nums2[i];
            if (mapB.containsKey(dest)) {
                mapB.put(dest, mapB.get(dest) + 1);
            } else {
                mapB.put(dest, 1);
            }
        }

        // 以短数组中出现的数字为准
        // 次数以两个数组中出现最少的为准
        if (a >= b) {
            for (int i = 0; i < b; i++) {
                long dest = nums2[i];
                if (mapA.containsKey(dest)) {
                    target.put(dest, Math.min(mapA.get(dest), mapB.get(dest)));
                }
            }
        } else {
            for (int i = 0; i < a; i++) {
                long dest = nums1[i];
                if (mapB.containsKey(dest)) {
                    target.put(dest, Math.min(mapA.get(dest), mapB.get(dest)));
                }
            }
        }

        List<Long> list = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : target.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                list.add(entry.getKey());
            }
        }

        int[] result = new int[list.size()];
        int i = 0;
        for (Long n : list) {
            result[i++] = n.intValue();
        }

        return result;
    }

    // 同样是哈希解法, 但是有优秀的方案
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        // 不管如何交集一定出现在两个数组中, 故对任意一个数组做完全哈希, 优化的版本可以只对数组较少的一个做哈希
        for (int num : nums1) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 遍历第二个数组时减少出现次数, 直到0时提出该数字
        for (int num : nums2) {
            if (map.containsKey(num)) {
                res.add(num);

                // 这步骤比较关键, 即出现次数降低为0
                // 先降为0说明这个出现频次最小
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }

        int[] result = new int[res.size()];
        int index = 0;
        for (int i : res) {
            result[index++] = i;
        }

        return result;
    }
}
