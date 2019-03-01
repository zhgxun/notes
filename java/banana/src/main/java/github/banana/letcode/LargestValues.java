package github.banana.letcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 515. 在每个树行中找最大值
 * <p>
 * 您需要在二叉树的每一行中找到最大的值
 * 其实就是二叉树安层遍历
 * <pre>
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 * </pre>
 * <p>
 * 输出 [1,3,9]
 */
public class LargestValues {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(3);
        node.left.left = new TreeNode(5);
        node.left.right = new TreeNode(3);
        node.right = new TreeNode(2);
        node.right.right = new TreeNode(9);

        System.out.println(new LargestValues().largestValues(node));
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 赋值为最小值
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 前面有蒜素判断其实这里不会出现空指针
                if (node == null) {
                    continue;
                }
                if (node.val > max) {
                    max = node.val;
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(max);
        }

        return result;
    }
}
