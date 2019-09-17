package github.banana.letcode;

import java.util.Arrays;

public class RemoveDuplicatesV4 {

    public static void main(String[] args) {
        // [0,0,1,1,1,2,2,3,3,4]
        // 0, 1, 2, 3, 4
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int length = nums.length;
        int k = 0;
        for (int i = 1; i < length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[++k] = nums[i];
            }
        }

        return k + 1;
    }
}
