package github.banana.letcode;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElementV2 {

    public static void main(String[] args) {
        // [3, 4, -1, 3]
        // 3, 4, -1, -1
        int[] nums = new int[]{1, 3, 4, 2};
        System.out.println(Arrays.toString(nextGreaterElements(nums)));
    }

    // 这个题确实有意思, 可能不是很理解循环数组的意思, 将错过解决这个题
    // 循环数组的意思就是下一个最大值不仅仅是右边的部分, 还有查找左边的部分了
    // 但是真正意义上的循环数组是不存在的, 内存结构是线性的, 只能抽象
    // 模拟环形数组, 一般是通过 % 运算符求模获得环形特效
    // 因此无需将数组人为扩大2倍, 当然如果确实不知道扩大两倍也是可以的, 只是增加了一倍的空间复杂度而已
    // 这个解法效率中等
    public static int[] nextGreaterElements(int[] nums) {
        int length = nums.length;
        int[] target = new int[length];
        Stack<Integer> stack = new Stack<>();
        // 注意下标的循环模拟即可
        for (int i = 2 * length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % length]) {
                stack.pop();
            }
            target[i % length] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % length]);
        }

        return target;
    }
}
