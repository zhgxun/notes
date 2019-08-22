package github.banana.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CombinationSum2 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 5, 2, 1, 2};
        System.out.println(new CombinationSum2().combinationSum2(nums, 5));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(candidates);

        findCombinationSum(candidates, 0, target, res, new Stack<>());

        return res;
    }

    private void findCombinationSum(int[] candidates, int start, int target, List<List<Integer>> res, Stack<Integer> stack) {
        // 回溯到目标值
        if (target == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }

        int length = candidates.length;
        for (int i = start; i < length; i++) {
            // 如果已经不满足小于0则退出
            if (target - candidates[i] < 0) {
                break;
            }

            // 这是去重的关键之处之一, 何时该数据不参与计算
            // 注意这个 i > start 条件, 即是要知道下标已经被处理过, 而 start 就是问我们每次记录的开始下标
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // 往栈中放入一个元素
            stack.add(candidates[i]);
            // 只能处理剩余数组部分的数据
            findCombinationSum(candidates, i + 1, target - candidates[i], res, stack);
            // 将处理完毕的元素释放掉
            stack.pop();
        }
    }
}
