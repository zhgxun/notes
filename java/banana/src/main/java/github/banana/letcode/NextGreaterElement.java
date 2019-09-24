package github.banana.letcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
    }

    // 单调栈数据结构专门用来解决下一个元素问题
    // nums1 是 nums2 的子集
    // 单调栈是很重要的解决一类问题的数据结构
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 使用单调栈来存储目标元素中下一个更大的元素
        int length = nums2.length;
        // 其实也可以不用栈, 做两层循环找到原始数组中每个元素的下一个更大元素即可
        // 该处存放的是每个位置下一个更大的元素
        // 由于原始数组不重复故都是唯一的存在
        Map<Integer, Integer> target = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = length - 1; i >= 0; i--) {
            // 每次对栈进行比较, 如果栈中元素更小则出栈
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            // 当前位置的下一个更大元素就是栈顶元素
            target.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }

        // 因为是子集肯定存在元素故直接获取即可
        int len = nums1.length;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = target.get(nums1[i]);
        }

        return result;
    }
}
