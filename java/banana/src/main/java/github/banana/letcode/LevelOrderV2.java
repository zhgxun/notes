package github.banana.letcode;

import github.banana.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderV2 {

    // 队列解法也容易理解
    // 二叉树的层次遍历需要使用队列来完成, 前序中序和后续使用栈或者递归
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            // 队列中保存一层的元素
            int size = queue.size();
            List<Integer> res = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                res.add(node.val);

                // 将当前层中的下一层元素全部入队
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

                size--;
            }

            // 保存目标元素
            result.add(res);
        }

        return result;
    }

    // 递归解法, 更容易理解
    // 需要注意怎么表示这个层
    public List<List<Integer>> levelOrderV2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        order(root, result, 0);
        return result;
    }

    private static void order(TreeNode node, List<List<Integer>> result, int depth) {
        if (node == null) {
            return;
        }

        // 可以不用太关心, 是在不行在每次添加元素前判断, 如果当前层不存在则初始化即可
        if (result.size() == depth) {
            result.add(new ArrayList<>());
        }

        // 将对应的值添加到对应的层中
        result.get(depth).add(node.val);

        if (node.left != null) {
            order(node.left, result, depth + 1);
        }
        if (node.right != null) {
            order(node.right, result, depth + 1);
        }
    }
}
