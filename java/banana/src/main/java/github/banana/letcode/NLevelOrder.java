package github.banana.letcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 429. N叉树的层序遍历
 * <p>
 * 给定一个 N 叉树, 返回其节点值的层序遍历
 * (即从左到右, 逐层遍历)
 */
public class NLevelOrder {

    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int length = queue.size();
            while (length-- > 0) {
                Node node = queue.poll();
                if (node == null) {
                    continue;
                }
                list.add(node.val);
                int size = node.children.size();
                if (size > 0) {
                    queue.addAll(node.children);
                }
            }
            lists.add(list);
        }

        return lists;
    }
}
