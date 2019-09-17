package github.banana.letcode;

import java.util.Arrays;

public class TwoSumV3 {

    public static void main(String[] args) {
        int[] num = new int[]{2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(num, 9)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int length = numbers.length;

        int left = 0;
        int right = length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (target == sum) {
                return new int[]{left + 1, right + 1};
            } else if (target > sum) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}
