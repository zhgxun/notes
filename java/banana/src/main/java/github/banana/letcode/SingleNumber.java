package github.banana.letcode;

import java.util.Arrays;

/**
 * 136. 只出现一次的数字
 * <p>
 * 给定一个非空整数数组, 除了某个元素只出现一次以外, 其余每个元素均出现两次, 找出那个只出现了一次的元素
 * <p>
 * 异或运算比较容易实现, 但不理解异或, 后续在学习, 故用评论区的排序查找算法来实现
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(new SingleNumber().singleNumber(nums));
    }

    public int singleNumber(int[] nums) {
        // 因为其它元素每个元素都出现了2次, 那一次升序排列后, 其实就相当好找了
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int length = nums.length;
        for (int i = 0; i < length; i += 2) {
            // 相邻的两个元素总是挨在一起
            // 或者遍历到末尾
            if ((i == length - 1) || (nums[i] != nums[i + 1])) {
                return nums[i];
            }

        }
        return -1;
    }
}
