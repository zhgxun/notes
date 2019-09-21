package github.banana.letcode;

import github.banana.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * <p>
 * 给定一个二叉树, 返回所有从根节点到叶子节点的路径
 *
 * <pre>
 *    1
 *  /   \
 * 2     3
 * / \
 * 4 5
 * </pre>
 */
public class BinaryTreePaths {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right = new TreeNode(3);
        System.out.println("[\"1->2->5\", \"1->3\"]");
        System.out.println(new BinaryTreePaths().binaryTreePaths(node));
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        helper(root, result, "");
        return result;
    }

    private void helper(TreeNode node, List<String> result, String path) {
        if (node == null) {
            return;
        }

        // 注意该变量的设计
        path += node.val + "->";

        // 必须到达叶子节点才算路径遍历完毕
        if (node.left == null && node.right == null) {
            result.add(path.substring(0, path.lastIndexOf("->")));
            return;
        }

        if (node.left != null) {
            helper(node.left, result, path);
        }
        if (node.right != null) {
            helper(node.right, result, path);
        }
    }
}
