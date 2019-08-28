package github.banana.letcode;

import java.util.Arrays;

public class MoveZeroes {

    public static void main(String[] args) {
        moveZeroes(new int[]{4, 2, 4, 0, 0, 3, 0, 5, 1, 0});
        moveZeroes(new int[]{0, 1, 0, 3, 12});
        moveZeroes(new int[]{1, 0, 0});
    }

    // 不要看错题目的意思, 是保留原有的非零相对顺序, 而不是有序, 我就是被这里给迷惑了
    // 时间复杂度为O(n), 已经相当的快了
    public static void moveZeroes(int[] nums) {
        int length = nums.length;
        // 记录新的有效下标, 因为最后一定是0则不需要单独记录临时变量
        int last = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] != 0) {
                nums[last++] = nums[i];
            }
        }
        // 将最后的部分补齐为0即可
        for (int j = last; j < length; j++) {
            nums[last++] = 0;
        }
    }
}
