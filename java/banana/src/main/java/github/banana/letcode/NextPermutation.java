package github.banana.letcode;

import java.util.Arrays;

public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        for (int i = 0; i < 10; i++) {
            nextPermutation(nums);
            System.out.println("i=" + i + ": " + Arrays.toString(nums));
        }
    }

    public static void nextPermutation(int[] nums) {
        /* [1][2][3][4] */
        int i = nums.length - 2;
        // 让数组保持满足从右边降序排列, 因为这样倒序后是最小的序列
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        // 如果存在i说明当前位置开始不满足降序排序
        if (i >= 0) {
            int j = nums.length - 1;
            // 由于此时右边已经是降序排序, 而当前i位置不满足, 则i将可以放入右边的一个位置中
            // 直接将这个位置和当前位置i进行交换, 保持右边降序
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // 交换这两个位置, 使右边降序排列
            swap(nums, i, j);
        }

        // 由于右边是降序, 则反转后是最小的序列, 组合后就是下一个字典序列的较大序列
        reverse(nums, i + 1);
    }

    private static void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
