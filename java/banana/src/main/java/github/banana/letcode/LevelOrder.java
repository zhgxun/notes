package github.banana.letcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {

    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        // 把树添加到队列中
        queue.add(root);
        // 队列不为空
        while (!queue.isEmpty()) {
            // 队列中的元素个数
            int count = queue.size();

            // 存储相同层级数据
            List<Integer> list = new ArrayList<>();
            while (count > 0) {
                // 出队
                TreeNode node = queue.poll();
                // 保存当前元素
                list.add(node.val);

                // 每次出队的时候顺便把下一层的节点添加进入队列中

                // 继续入队
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                count--;
            }

            // 保存当前层级元素
            res.add(list);
        }
        return res;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
