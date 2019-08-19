package github.banana.letcode;

import java.util.Arrays;

/**
 * 原地移除数组中重复的项, 并返回数组的新长度
 */
public class RemoveDuplicatesV2 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int length = nums.length;
        // 注意理解, 应该是找最新的下标, 而不是记忆原有的下标
        int j = 0;
        for (int i = 1; i < length; i++) {
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
            }
        }

        return j + 1;
    }
}
