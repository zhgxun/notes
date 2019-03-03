package github.banana.letcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 590. N叉树的后序遍历
 * <p>
 * 给定一个 N 叉树, 返回其节点值的后序遍历
 */
public class Postorder {

    public static void main(String[] args) {

    }

    public List<Integer> postorder1(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        stack.add(root);
        while (!stack.isEmpty()) {
            // 查看栈顶元素但不移除该元素
            Node curr = stack.peek();
            if (curr == null || curr.children == null) {
                continue;
            }
            // 当前节点已经没有子节点--到达叶子节点处
            // 或者往上遍历处--当前节点包括上一个节点
            if ((curr.children.size() == 0) || (pre != null && (curr.children.contains(pre)))) {
                list.add(curr.val);
                pre = curr;
                // 开始出栈
                stack.pop();
            } else {
                // 全部入栈
                for (int i = curr.children.size() - 1; i >= 0; i--) {
                    stack.push(curr.children.get(i));
                }
            }
        }

        return list;
    }

    private List<Integer> list = new ArrayList<>();

    // 递归解法
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return list;
        }

        int size = root.children.size();
        if (size > 0) {
            for (Node node : root.children) {
                postorder(node);
            }
        }

        list.add(root.val);
        return list;
    }
}
