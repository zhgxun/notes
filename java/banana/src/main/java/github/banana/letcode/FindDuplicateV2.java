package github.banana.letcode;

import java.util.Arrays;

public class FindDuplicateV2 {

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println(findDuplicate(new int[]{3, 1, 3, 4, 2}));
    }

    // 正常人的解法, 排序, 哈希
    public static int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }

        return -1;
    }
}
