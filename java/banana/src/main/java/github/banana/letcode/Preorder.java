package github.banana.letcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 589. N叉树的前序遍历
 * <p>
 * 给定一个 N 叉树，返回其节点值的前序遍历
 */
public class Preorder {

    public static void main(String[] args) {

    }

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.empty()) {
            Node node = stack.pop();
            list.add(node.val);
            if (node.children != null) {
                int size = node.children.size();
                for (int i = size - 1; i >= 0; i--) {
                    stack.add(node.children.get(i));
                }
            }
        }

        return list;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
