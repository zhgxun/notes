package github.banana.letcode;

import java.util.Arrays;

public class RemoveDuplicatesV3 {

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int[] nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }

        // 基准元素, 用于记录重复的元素出现的个数
        int base = nums[0];
        // 重复元素出现的个数
        int baseCount = 1;
        // 记录表的下标
        int j = 1;

        for (int i = 1; i < len; i++) {
            // 跟基准元素不相符
            if (nums[i] != base) {
                nums[j++] = nums[i];

                // 基准初始化
                base = nums[i];
                baseCount = 1;
            } else {
                // 重复的元素, 记录重复次数
                baseCount += 1;
                // 最多重复2次, 其它次数不处理
                if (baseCount == 2) {
                    nums[j++] = nums[i];
                }
            }
        }
        return j;
    }
}
