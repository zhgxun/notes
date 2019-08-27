package github.banana.letcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MissingNumber {

    public static void main(String[] args) {
        System.out.println(missingNumberV2(new int[]{3, 0, 1}));
        System.out.println(missingNumberV2(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(missingNumberV2(new int[]{0}));
    }

    // 万年不变的桶排序解法
    public static int missingNumber(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            // 当前数是否应该在属于自己的位置上
            while (nums[i] < length && nums[i] != i && nums[nums[i]] != nums[i]) {
                swap(nums, i, nums[i]);
                System.out.println(Arrays.toString(nums));
            }
        }
        // 二次遍历跟位置不对应的则为缺失的数字
        for (int i = 0; i < length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return length;
    }

    // 交换两个元素的位置
    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    // 一次排序法未超时, 但是效率相当低
    public static int missingNumberV2(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return length;
    }

    // 一次哈希法, 效率更慢, 时间复杂度O(n), 空间复杂度O(n)
    public static int missingNumberV3(int[] nums) {
        int length = nums.length;
        Set<Integer> set = new HashSet<>(length);
        for (int i = 0; i < length; i++) {
            set.add(nums[i]);
        }
        for (int i = 0; i < length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return length;
    }

    // 数学方法, 高斯求和函数求和, 将去其中的每一个数字, 最后剩下的余数就是确实的数字
    // 时间复杂度O(n) 空间复杂度O(1)
    // 这个基本就是最优解法了
    public static int missingNumberV4(int[] nums) {
        int length = nums.length;
        // 高斯求和 0-n 之间的求和
        int sum = length * (length + 1) / 2;
        // 当前数组的和
        int haveSum = 0;
        for (int i = 0; i < length; i++) {
            haveSum += nums[i];
        }
        // 差值即为缺失的正整数
        return sum - haveSum;
    }
}
