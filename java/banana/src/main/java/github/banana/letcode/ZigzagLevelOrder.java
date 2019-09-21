package github.banana.letcode;

import github.banana.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigzagLevelOrder {

    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        System.out.println(result.size());
    }

    // 递归方式实现, 递归如果能理解其实非常简单, 但如果不理解就无法写出处理流程
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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
        if (result.size() == depth) {
            result.add(new ArrayList<>());
        }

        // 将当前元素添加到该层中, 奇数层反转插入
        if (depth % 2 == 0) {
            result.get(depth).add(node.val);
        } else {
            result.get(depth).add(0, node.val);
        }

        order(node.left, result, depth + 1);
        order(node.right, result, depth + 1);
    }

    // 队列方式实现
    public List<List<Integer>> zigzagLevelOrderV2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> res = new ArrayList<>();

            // 看当前待插入的层是否为奇数层需要反转
            // 当前是偶数层则下一层为奇数层需要反转
            boolean flag = result.size() % 2 == 0;
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                if (flag) {
                    res.add(node.val);
                } else {
                    res.add(0, node.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

                size--;
            }
            result.add(res);
        }

        return result;
    }
}
