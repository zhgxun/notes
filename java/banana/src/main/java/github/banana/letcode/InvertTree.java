package github.banana.letcode;

import github.banana.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. 翻转二叉树
 * <p>
 * 这个问题是受到 Max Howell 的 原问题 启发的
 * <p>
 * 谷歌 我们90％的工程师使用您编写的软件(Homebrew), 但是您却无法在面试时在白板上写出翻转二叉树这道题, 这太糟糕了
 * <p>
 * <p>
 * 原始二叉树
 * <pre>
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * </pre>
 * <p>
 * 翻转后的二叉树
 * <pre>
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * </pre>
 * <p>
 * 每个节点只存储着子树的引用地址, 必须通过节点获取到子树, 一直到叶子节点为止
 * 二叉树比较奇特, 需要理解结构和存储方式, 要不然脑海中始终存储的一棵树, 但是代码始终没有树, 一定要抛开感官上的错觉
 */
public class InvertTree {

    public static void main(String[] args) {

    }

    // 借助队列解法, 跟遍历一致
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                TreeNode left = node.left;
                node.left = node.right;
                node.right = left;

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return root;
    }

    // 递归解法
    // 递归优雅而且很多时候相当快速
    public TreeNode invertTree1(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        // 直接交换
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        if (root.left != null) {
            invertTree1(root.left);
        }
        if (root.right != null) {
            invertTree1(root.right);
        }

        return root;
    }
}
