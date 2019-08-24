package github.banana.letcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDisappearedNumbersV2 {

    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        System.out.println(findDisappearedNumbersV2(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // 由于要在数组范围内, 那想到一个办法, 去除重复的部分, 不存在的就是确实的
        Set<Integer> set = new HashSet<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            set.add(nums[i]);
        }
        for (int j = 1; j <= length; j++) {
            // 在1-n之间不存在的即是缺失的数据
            if (!set.contains(j)) {
                res.add(j);
            }
        }
        return res;
    }

    public static List<Integer> findDisappearedNumbersV2(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int length = nums.length;

        // 桶排序
        // 桶排序太有用了, 尤其是对固定位置进行操作的情形
        // 深刻理解桶排序的思想
        // 1. 当前位置是否应该就是它的位置
        // 2. 应该是它的位置的地方是不是已经是它
        for (int i = 0; i < length; i++) {
            // nums[i] i+1 不相等说明不再自己该在的位置上
            // nums[nums[i] - 1] nums[i]
            // nums[i] - 1 是它应该在的自己的位置, 如果不相等则需要进行交换
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // 确实的部分就是跟下标不相符的部分
        for (int j = 0; j < length; j++) {
            if (nums[j] != j + 1) {
                res.add(j + 1);
            }
        }

        return res;
    }

    // 交换占用O(1)常数空间
    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
