package github.banana.letcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历二叉树
 * <p>
 * 遍历二叉树比较有意思, 哈哈, 非常符合栈的特性, 比较好玩
 */
public class InorderTraversal {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(8);
        node.right = new TreeNode(9);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(5);
        node.right.right.left = new TreeNode(3);

        System.out.println(inorderTraversal(node));
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 一直入栈到叶子节点, 先左子树入栈, 再右子树入栈
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                // 到达叶子节点, 出栈
                cur = stack.pop();
                list.add(cur.val);
                // 注意这个是对应节点的右子树
                cur = cur.right;
            }
        }
        return list;
    }
}
