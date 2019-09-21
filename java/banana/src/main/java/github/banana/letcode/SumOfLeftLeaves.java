package github.banana.letcode;

import github.banana.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 404. 左叶子之和
 * <p>
 * 计算给定二叉树的所有左叶子之和
 *
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 * <p>
 * 在这个二叉树中, 有两个左叶子, 分别是 9 和 15, 所以返回 24
 */
public class SumOfLeftLeaves {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(9);
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(0);
        node.right.left.left = new TreeNode(-7);
        node.right.left.left.left = new TreeNode(-1);

        System.out.println(new SumOfLeftLeaves().sumOfLeftLeaves(node));
    }

    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;

        if (root == null) {
            return sum;
        }

        // 使用两个队列, 一个做树的遍历, 一个用来标识当前节点是否为左子树的叶子节点
        // 入队列的数据数量是一一对应的
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Queue<Boolean> isLeft = new LinkedList<>();
        isLeft.add(false);
        while (!queue.isEmpty() && !isLeft.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                continue;
            }

            Boolean left = isLeft.poll();
            if (left == null) {
                continue;
            }

            // 计算左叶子节点的值和
            if (left && node.left == null && node.right == null) {
                sum += node.val;
            }

            if (node.left != null) {
                isLeft.add(true);
                queue.add(node.left);
            }

            if (node.right != null) {
                isLeft.add(false);
                queue.add(node.right);
            }
        }

        return sum;
    }
}
