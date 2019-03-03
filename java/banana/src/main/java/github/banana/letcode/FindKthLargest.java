package github.banana.letcode;

import java.util.Arrays;

/**
 * 215. 数组中的第K个最大元素
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素
 * 请注意, 你需要找的是数组排序后的第 k 个最大的元素, 而不是第 k 个不同的元素
 */
public class FindKthLargest {

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(new FindKthLargest().findKthLargest(nums, 4));
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int length = nums.length;
        return nums[length - k];
    }
}
