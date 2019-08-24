package github.banana.letcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateV2 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(containsDuplicate(nums));
    }

    public static boolean containsDuplicate(int[] nums) {
        // 一遍hash法效率不高, 空间占用太大
        int length = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }

    public static boolean containsDuplicateV2(int[] nums) {
        // 一次排序法, 对数时间复杂度, 已经快了很多
        int length = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
