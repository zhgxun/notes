package com.github.zhgxun.learn.notes.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题03. 数组中重复的数字
 * <p>
 * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 */
public class FindRepeatNumber {

    // 最容易想到也是最普通的解法
    // 缺点就是性能不够好
    // 需要记录已遍历的数字消耗额外的空间
    public int findRepeatNumberV2(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (Integer num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }

        return -1;
    }

    // 第二种解法
    // 不占用额外的时间
    // 数字在 0 ~ n - 1 范围内刚好对应下标, 因此将数组自身作为哈希表来使用
    // 每次遍历处理2个数字
    public int findRepeatNumber(int[] nums) {
        /*
         * 对于样例: 2, 3, 1, 0, 2, 5, 3
         *
         * | 2 | 3 | 1 | 0 | 2 | 5 | 3 |
         * |---------------------------|
         * | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
         */
        for (int index = 0; index < nums.length; index++) {
            // 索引和值相同无法确定是否重复出现
            if (index != nums[index]) {
                int value = nums[index];
                // 但是如果值为索引的位置也存在值那么数字一定重复了
                if (nums[value] == value) {
                    return value;
                } else {
                    nums[index] = nums[value];
                    nums[value] = value;
                }
            }
        }

        return -1;
    }
}
