package github.banana.letcode;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    public static void main(String[] args) {
        int[] nums = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        // 1, 1, 4, 2, 1, 1, 0, 0
        System.out.println(Arrays.toString(dailyTemperatures(nums)));
    }

    // 单调栈的一种抽象解法
    // 普通的单调栈存储值, 而此时的单调栈存储下标即是元素的位置
    // 效率中等不是最优解
    // 执行用时 :67 ms, 在所有 Java 提交中击败了70.70%的用户
    // 内存消耗 :42.4 MB, 在所有 Java 提交中击败了94.53%的用户
    public static int[] dailyTemperatures(int[] T) {
        int length = T.length;
        // 默认为0天, 因为有些元素处理不了
        int[] target = new int[length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            // 借助栈结构来减少一个非必要的遍历
            // 如果栈中的顶位置的元素小于当前值则说明遇见了一个更高的温度, 符合条件
            // 则当前位置减去上一次的位置就是距离的天数
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int n = stack.peek();
                // 位置出栈已为该位置找到了温度更高的天数
                target[stack.pop()] = i - n;
            }
            // 所有下标都要入栈参与查找
            stack.push(i);
        }

        return target;
    }

    // 这个解法效率最优
    // 执行用时 :5 ms, 在所有 Java 提交中击败了99.83%的用户
    // 内存消耗 :42.7 MB, 在所有 Java 提交中击败了93.22%的用户
    // 相比单调栈这种解法确实会减少一些步骤
    // 而且是比单调栈更好理解的部分, 需要处理边界条件
    public int[] dailyTemperaturesV2(int[] T) {
        int[] ans = new int[T.length];
        // 这种解法其实也是借助单调栈的思路, 即从后往前遍历, 变向的记录了下一个更大时间的天数, 因此可以跳过这些元素直接比较
        // 跟单调栈的相比优势应该是不借助栈空间吧
        // 从后往前遍历, 最后一个元素是0天无需处理
        for (int i = T.length - 2; i >= 0; i--) {
            // 温度超过最大温度的无需处理
            if (T[i] < 100) {
                // 当前元素往后找较大的温度, 每次步进是上一个的计算结果
                for (int j = i + 1; j < T.length; j += ans[j]) {
                    // 直接比较如果找到更大的温度则是目标温度
                    // 需要注意当前位置如果已经等不到最大温度, 则后续也没有最大温度否则死循环
                    // 即是它都没更高的温度了, 那你比它还高更不会有更高的温度了
                    if (T[j] > T[i]) {
                        ans[i] = j - i;
                        break;
                    } else if (ans[j] == 0) {
                        ans[i] = 0;
                        break;
                    }
                }
            }
        }

        return ans;
    }
}
