package github.banana.letcode;

import github.banana.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PostorderTraversalV2 {

    // 后续遍历递归依然是经典的解法
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        order(root, result);
        return result;
    }

    private static void order(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        order(node.left, result);
        order(node.right, result);
        result.add(node.val);
    }

    // 栈的解法
    // 注意入队的顺序, 因为是先打印左子树的节点值
    public List<Integer> postorderTraversalV2(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        // 往链表末尾添加元素
        stack.add(root);
        while (!stack.isEmpty()) {
            // 链表的最后一个数, 右节点先出
            TreeNode node = stack.pollLast();
            if (node == null) {
                continue;
            }
            // 往链表头部增加元素
            // 直接往链表头部方向插入元素即可
            result.addFirst(node.val);

            // 存储到链表后右子树节点先出
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }

        return result;
    }

}
