package github.banana.letcode;

import github.banana.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversalV2 {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = null;
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);
        node.right.right = null;

        System.out.println(inorderTraversalV2(node));
    }

    // 递归的方式解题
    // 递归是经典的解法
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        order(root, result);
        return result;
    }

    // 递归的方式解决
    private static void order(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        order(node.left, result);
        result.add(node.val);
        order(node.right, result);
    }

    // 用栈的方式解题
    // 栈的解题需要注意的是始终先将一个节点的左子树入栈, 在依次取出来值并继续处理右子树
    // 效率依然不低
    public static List<Integer> inorderTraversalV2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // 必须将当前节点的左子树一并入栈后在处理
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }

        return result;
    }
}
