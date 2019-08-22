package github.banana.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CombinationSum {

    private List<List<Integer>> res = new ArrayList<>();
    private int[] candidates;
    private int len;

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 6, 7};
        System.out.println(new CombinationSum().combinationSum(nums, 7));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        // 优化添加的代码1：先对数组排序，可以提前终止判断
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombinationSum(target, 0, new Stack<>());
        return res;
    }

    /**
     * 回溯方式遍历元素, 不满足条件时退出
     *
     * @param residue 剩余值
     * @param start   开始下标
     * @param pre     已遍历元素
     */
    private void findCombinationSum(int residue, int start, Stack<Integer> pre) {
        // 剩余值为0说明满足条件
        if (residue == 0) {
            // Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
            res.add(new ArrayList<>(pre));
            return;
        }

        // 优化添加的代码2：在循环的时候做判断，尽量避免系统栈的深度
        // residue - candidates[i] 表示下一轮的剩余，如果下一轮的剩余都小于 0 ，就没有必要进行后面的循环了
        // 这一点基于原始数组是排序数组的前提，因为如果计算后面的剩余，只会越来越小
        for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
            pre.add(candidates[i]);
            // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
            // 每次用一个元素去回溯递归, 知道返回和递归结束
            findCombinationSum(residue - candidates[i], i, pre);
            // 因为栈里面保存的是目标值, 回溯完了如果满足条件已经被保存, 不满足条件也不能继续保存数值, 需要释放, 出队即可
            pre.pop();
        }
    }
}
