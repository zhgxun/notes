package github.banana.letcode;

import github.banana.common.TreeNode;

/**
 * 114. 二叉树展开为链表
 * <p>
 * 给定一个二叉树, 原地将它展开为链表
 *
 * <pre>
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 * </pre>
 */
public class Flatten {

    public static void main(String[] args) {

    }

    public void flatten(TreeNode root) {
        // 往右子树递归拼接
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        if (root.left != null) {
            // 记录右节点
            TreeNode right = root.right;
            root.right = root.left;
            // 将左节点置空
            root.left = null;
            // 遍历右节点到最右叶子节点
            // 此时的 root.right 是上一次的左右左子树
            TreeNode node = root.right;
            while (node.right != null) {
                node = node.right;
            }
            node.right = right;
        }
    }
}
