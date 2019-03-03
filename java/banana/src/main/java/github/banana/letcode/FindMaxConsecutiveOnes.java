package github.banana.letcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 485. 最大连续1的个数
 * <p>
 * 给定一个二进制数组, 计算其中最大连续1的个数
 */
@Slf4j
public class FindMaxConsecutiveOnes {

    public static void main(String[] args) {
//        int[] nums = {0, 1};
        int[] nums = {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(new FindMaxConsecutiveOnes().findMaxConsecutiveOnes(nums));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        int length = nums.length;
        for (int i = 0, j = 0; j < length; j++) {
            if (nums[i] == 1 && nums[j] == 1) {
                count++;
                max = max > count ? max : count;
            } else {
                if (j == 0) {
                    i++;
                } else {
                    count = 1;
                    i = j;
                }
                if (i >= length) {
                    break;
                }
            }
        }

        return max;
    }
}
