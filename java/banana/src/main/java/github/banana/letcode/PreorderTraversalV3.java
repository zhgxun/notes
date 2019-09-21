package github.banana.letcode;

import github.banana.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreorderTraversalV3 {

    // 前序遍历递归依然是经典的解法
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        order(root, result);
        return result;
    }

    private static void order(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        order(node.left, result);
        order(node.right, result);
    }

    // 栈的解法
    // 前序遍历需要注意入栈的顺序, 右子树先入栈, 左子树再入栈, 出栈时先出左子树
    public List<Integer> preorderTraversalV2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            // 注意空的判断
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }

            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }
}
