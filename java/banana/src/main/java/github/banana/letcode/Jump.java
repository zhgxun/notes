package github.banana.letcode;

public class Jump {

    public static void main(String[] args) {
        System.out.println(jump(new int[]{1, 2, 1, 1}));
    }

    public static int jump(int[] nums) {
        // 记录当前能跳到的最大位置
        int end = 0;
        // 记录遍历期间的可选的最大长度
        int maxPosition = 0;
        // 跳跃的步数
        int steps = 0;
        // 注意边界
        for (int i = 0; i < nums.length - 1; i++) {
            // 当前能跳到的最大位置就是当前下标的值加上已处理过的下标
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) {
                // 遍历一次跳跃后找到的最大位置
                end = maxPosition;
                // 首次加了一步, 最后一步不需要加, 所以最后一个数不需要处理
                steps++;
            }
        }
        return steps;
    }
}
