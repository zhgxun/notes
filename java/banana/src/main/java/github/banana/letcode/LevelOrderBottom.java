package github.banana.letcode;

import github.banana.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. 二叉树的层次遍历 II
 * <p>
 * 给定一个二叉树, 返回其节点值自底向上的层次遍历(即按从叶子节点所在层到根节点所在的层, 逐层从左向右遍历)
 *
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 遍历结果为
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 * </pre>
 * <p>
 * list add 方法还有这个参数, 我也是第一次见识
 * <p>
 * 在此列表中的指定位置插入指定的元素(可选操作)
 * 将当前位于该位置的元素(如果有)和任何后续元素右移(在其索引中添加一个元素)
 */
public class LevelOrderBottom {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        System.out.println(levelOrderBottom(node));
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 队列里面始终保持同一层级的值, 而且按照展示顺序入队
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 当前层级的元素个数
            int len = queue.size();
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                // 将当前层级的元素按左子树-右子树的方向入队
                TreeNode t = queue.poll();
                // 只有非 null 的值才会被入队, 通常不存在空指针
                result.add(t.val);

                // 如果存在左子树则左子树入队
                if (t.left != null) {
                    queue.offer(t.left);
                }
                // 右子树入队
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
            // 这个功能按顺序往后插入元素
            res.add(0, result);
        }
        return res;
    }
}
