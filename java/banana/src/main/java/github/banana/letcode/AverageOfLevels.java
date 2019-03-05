package github.banana.letcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 637. 二叉树的层平均值
 * <p>
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组
 *
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 * [3, 14.5, 11]
 * <p>
 * 二叉树层次遍历要用队列, 前序中序后续相关的则必须借助栈数据结构
 */
public class AverageOfLevels {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        System.out.println(new AverageOfLevels().averageOfLevels(node));
    }

    private List<Double> list = new ArrayList<>();

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double val = 0;
            int length = size;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                val += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(val / length);
        }

        return list;
    }
}
