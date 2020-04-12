package com.github.zhgxun.learn.notes.leetcode;

/**
 * 面试题04. 二维数组中的查找
 * <p>
 * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 */
public class FindNumberIn2DArray {

    public static void main(String[] args) {
        System.out.println(find(new int[]{1, 4, 7, 11, 15}, 20));
        System.out.println(find(new int[]{2, 5, 8, 12, 19}, 20));
        System.out.println(find(new int[]{3, 6, 9, 16, 22}, 20));
        System.out.println(find(new int[]{10, 13, 14, 17, 24}, 20));
        System.out.println(find(new int[]{18, 21, 23, 26, 30}, 20));
    }

    // 行二分法查找
    // 列也是递增, 列应该也进行二分
    // 看了下别人的题解是依次递进, 不过逐行二分效率也很高呀, 只是用不上列也是递增的优势
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int[] line : matrix) {
            if (find(line, target)) {
                return true;
            }
        }

        return false;
    }

    private static boolean find(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
