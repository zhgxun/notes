package github.banana.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 求子数组的最小值之和, 没看懂
 * https://leetcode-cn.com/problems/sum-of-subarray-minimums/
 */
public class SumSubarrayMins {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 2, 4};
        System.out.println(sumSubarrayMinsV3(nums));
//        System.out.println(allArr(nums));
    }

    // 暴力法 100个单元测试执行到82个时超出时间限制, 故效率太低
    public static int sumSubarrayMins(int[] A) {
//        Set<Set<Integer>> sets = genSubArr(A);
//        int minSum = 0;
//        for (Set<Integer> set : sets) {
//            int min = Integer.MAX_VALUE;
//            for (Integer num : set) {
//                if (num < min) {
//                    min = num;
//                }
//            }
//            minSum += min;
//        }
//
//        return minSum;

        List<List<Integer>> lists = genSubArrV2(A);
        int minSum = 0;
        for (List<Integer> list : lists) {
            int min = Integer.MAX_VALUE;
            for (Integer num : list) {
                if (num < min) {
                    min = num;
                }
            }
            minSum += min;
        }

        return minSum;
    }

    // 维护一个最小值栈
    public static int sumSubarrayMinsV2(int[] A) {
        int MOD = 1_000_000_007;

        Stack<RepInteger> stack = new Stack<>();
        int ans = 0, dot = 0;
        for (int j = 0; j < A.length; ++j) {
            // Add all answers for subarrays [i, j], i <= j
            int count = 1;
            while (!stack.isEmpty() && stack.peek().val >= A[j]) {
                RepInteger node = stack.pop();
                count += node.count;
                dot -= node.val * node.count;
            }
            stack.push(new RepInteger(A[j], count));
            dot += A[j] * count;
            ans += dot;
            ans %= MOD;
        }

        return ans;
    }

    static class RepInteger {
        int val, count;

        RepInteger(int v, int c) {
            val = v;
            count = c;
        }
    }

    /**
     * 生成数组无重复的所有子数组, 性能明显不够好
     * <p>
     * 去除重复的一个好方法是排序, 去重重复的元素, 或者做一次hash即可去重重复的元素, 不要改变原始数组即可
     *
     * @param nums 原始数组
     * @return 生成的目标数组
     */
    private static Set<Set<Integer>> genSubArr(int[] nums) {
        Set<Set<Integer>> sets = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(nums[i]);
            sets.add(new HashSet<>(set));
            for (int j = i + 1; j < nums.length; j++) {
                set.add(nums[j]);
                sets.add(new HashSet<>(set));
            }
        }

        return sets;
    }

    /**
     * 包含重复子数组
     *
     * @param nums 目标数组
     * @return 所有子数组
     */
    private static List<List<Integer>> genSubArrV2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            lists.add(new ArrayList<>(list));
            for (int j = i + 1; j < nums.length; j++) {
                list.add(nums[j]);
                lists.add(new ArrayList<>(list));
            }
        }

        return lists;
    }

    private static List<List<Integer>> allArr(int[] nums) {
        List<List<Integer>> target = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        genSubArrV3(target, stack, nums, 0, nums.length);

        return target;
    }

    /**
     * 递归方式生成数组的所有子数组
     * 但是显然递归的时间复杂度比迭代的要长
     *
     * @param target 存储目标子数组
     * @param stack  借助栈来临时保存需要生成的子数组
     * @param nums   原始数组
     * @param depth  深度, 这个深度需要注意的是, 有点类似树的遍历, 树的根才是有效的元素, 枝丫上有很多重复的元素不能保存
     * @param length 原始数组的长度, 即是根的深度
     */
    private static void genSubArrV3(List<List<Integer>> target, Stack<Integer> stack, int[] nums, int depth, int length) {
        // 其它都很好理解, 就是这一步要怎么取舍的问题, 纠结了我很久
        // 理解为树的前序遍历即可, 树的叶子节点上的位置为有效元素即可
        // 递归方式如果想清楚了特别简单简洁, 所有的递归都可以用迭代完成, 但是迭代可能也未必比递归好理解
        // 比如二叉树的几种遍历, 递归相当简洁, 但是迭代却是三种不同的操作方式, 挺经典的
        if (depth == length) {
            target.add(new ArrayList<>(stack));
            return;
        }

        stack.push(nums[depth]);
        genSubArrV3(target, stack, nums, depth + 1, length);
        stack.pop();
        genSubArrV3(target, stack, nums, depth + 1, length);
    }

    private static int sumSubarrayMinsV3(int[] A) {
        int MOD = 1_000_000_007;
        int N = A.length;

        // prev has i* - 1 in increasing order of A[i* - 1]
        // where i* is the answer to query j
        Stack<Integer> stack = new Stack<>();
        int[] prev = new int[N];
        for (int i = 0; i < N; ++i) {
            while (!stack.isEmpty() && A[i] <= A[stack.peek()]) {
                stack.pop();
            }
            prev[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        System.out.println("prev: " + Arrays.toString(prev));

        // next has k* + 1 in increasing order of A[k* + 1]
        // where k* is the answer to query j
        stack = new Stack<>();
        int[] next = new int[N];
        for (int k = N - 1; k >= 0; --k) {
            while (!stack.isEmpty() && A[k] < A[stack.peek()]) {
                stack.pop();
            }
            next[k] = stack.isEmpty() ? N : stack.peek();
            stack.push(k);
        }
        System.out.println("next: " + Arrays.toString(next));

        // Use prev/next array to count answer
        long ans = 0;
        for (int i = 0; i < N; ++i) {
            ans += (i - prev[i]) * (next[i] - i) % MOD * A[i] % MOD;
            ans %= MOD;
        }
        return (int) ans;
    }
}
