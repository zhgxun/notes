package github.banana.demo;

public class Pow {
    public static void main(String[] args) {
        // System.out.println(myPow(2.0, 100));
        // int[] nums = { 3, 2, 2, 3 };
        // System.out.println(removeElement(nums, 3));
        // String a = "hello";
        // String b = "ll";
        // System.out.println(strStr(a, b));
        // int[] nums = { 1, 3, 5, 6 };
        // System.out.println(searchInsert(nums, 0));
        System.out.println(mySqrt(-9));
    }

    /**
     * 非负整数的平方根
     * 
     * @param x
     * @return
     */
    private static int mySqrt(int x) {
        // 转化为更高精度类型
        double n, tmp;
        // 保存结果
        double d = 0.0;
        // 0,1 返回本身
        if (x == 0 || x == 1) {
            return x;
        }

        n = (double) x;
        while (Math.abs(n - d) > 0.000001) {
            tmp = n;
            n = 0.5 * (tmp + (double) x / tmp);
            d = tmp;
        }
        return (int) d;
    }

    /**
     * 从升序数组中找出一个值, 如果存在则返回第一次查找到的下标, 如果没有则返回它应该存在的值
     * 
     * @param nums
     * @param target
     * @return
     */
    private static int searchInsert(int[] nums, int target) {
        // 是否是第一个或者最后一个
        if (nums[0] >= target) {
            return 0;
        }
        int length = nums.length;
        if (nums[length - 1] == target) {
            return length - 1;
        }
        if (nums[length - 1] < target) {
            return length;
        }
        // 否则目标数组在中间
        for (int i = 0; i < length; i++) {
            // 数组有序, 则遇见第一个值为止, 可能不存在需要插入该位置
            if (nums[i] >= target) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 查找字符串首次出现的位置
     * 
     * @param haystack
     * @param needle
     * @return
     */
    private static int strStr(String haystack, String needle) {
        for (int i = 0;; i++) {
            for (int j = 0;; j++) {
                if (j == needle.length()) {
                    return i;
                }
                if (i + j == haystack.length()) {
                    System.out.println(222);
                    return -1;
                }
                // 开头的字符串不一致, 退出本次循环, 这是关键, 否则说明字符串是一致的
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }
            }
        }
    }

    /**
     * 删除数组重复的元素
     * 
     * @param nums
     * @param val
     * @return
     */
    private static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 计算平方
     * 
     * @param x
     * @param n
     * @return
     */
    private static double myPow(double x, int n) {
        // long类型对待
        long N = n;
        // 如果指数小于0, 则转为正数对待
        if (N < 0) {
            // 基础变为分数
            x = 1 / x;
            // 指数变为正数
            N = -N;
        }
        // 初始值1
        double ans = 1;
        double current_product = x;
        // 让指数逐渐减少2倍
        for (long i = N; i > 0; i /= 2) {
            // 奇数时
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            // 偶数都让底数成倍相乘
            current_product = current_product * current_product;
        }
        return ans;
    }
}
