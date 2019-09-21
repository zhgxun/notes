package github.banana.letcode;

import github.banana.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 113. 路径总和
 * <p>
 * 给定一个二叉树和一个目标和, 找到所有从根节点到叶子节点路径总和等于给定目标和的路径
 */
public class PathSum {

    public static void main(String[] args) {

    }

    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> stack = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        helper(root, sum);
        return result;
    }

    private void helper(TreeNode root, int sum) {
        if (root == null) {
            return;
        }

        stack.add(root.val);
        if (sum == root.val) {
            // 必须到叶子节点, 将符合条件的链表存入数组中
            if (root.left == null && root.right == null) {
                result.add(new ArrayList<>(stack));
            }
        }
        if (root.left != null) {
            helper(root.left, sum - root.val);
        }
        if (root.right != null) {
            helper(root.right, sum - root.val);
        }
        // 最后一个元素踢出, 因为无法保证另一个分支是否也符合要求, 但当前叶子节点是不符合要求的
        // 故去除当前叶子节点
        stack.pollLast();
    }
}
