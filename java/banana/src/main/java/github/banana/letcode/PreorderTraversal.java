package github.banana.letcode;

import github.banana.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. 二叉树的前序遍历
 * <p>
 * 给定一个二叉树，返回它的 前序 遍历
 *
 * <pre>
 *    1
 *     \
 *      2
 *     /
 *    3
 * </pre>
 * <p>
 * [1,2,3]
 */
public class PreorderTraversal {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.right = new TreeNode(2);
        System.out.println(new PreorderTraversal().preorderTraversal(node));
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            list.add(node.val);
            // 注意入队顺序, 先入右子节点, 因为要先出左子树节点
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }

        return list;
    }
}
