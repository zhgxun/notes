package github.banana.letcode;

public class MinSubArrayLenV2 {

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    // 双指针法, 有一个技巧是需要注意的, 就是每次用一个元素往后找的时候, 一旦找到一个位置满足大于等于目标值, 则该元素生命周期结束
    // 而这个累加和下次如何使用呢, 注意其实我们只要减去这个元素剩下的累加和依然还是有效的, 一定要注意到这点
    // 时间复杂度 O(n) 空间复杂度 O(1) while 其实是原地进行
    // 如果想不到这种接法, 可以想到优化的暴力解法, 就是提前存储累加和, 用O(n^2)来解决我觉得也是可以的, 不过不是最优解
    public static int minSubArrayLen(int s, int[] nums) {
        int length = nums.length;
        // 记录最小长度
        int min = Integer.MAX_VALUE;
        // 记录当前被操作元素的下标
        int left = 0;
        // 累加和, 这个累加和很重要, 每次只需要减掉当前被操作的元素剩下的累加和是有效的
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            // 这个循环很重要, 也是解题的关键步骤
            // 因为此时数组下标已经移动到后面, 无法在从从前面开始, 此时丢弃上一次的元素
            // 循环保证这个区间的长度是最小的, 因为可能继续移动 left 还能使区间长度更小
            // 保证区间累计和大于等于目标值, 就是这个循环的体现
            while (sum >= s) {
                // 满足条件时最小的长度为 i+1-left
                min = Math.min(min, i + 1 - left);
                sum -= nums[left];
                left++;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
