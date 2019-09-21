package github.banana.letcode;

import github.banana.common.TreeNode;

/**
 * 687. 最长同值路径
 * <p>
 * 给定一个二叉树, 找到最长的路径, 这个路径中的每个节点具有相同值
 * 这条路径可以经过也可以不经过根节点
 */
public class LongestUnivaluePath {

    public static void main(String[] args) {

    }

    private int ans;

    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        arrowLength(root);
        return ans;
    }

    private int arrowLength(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = arrowLength(node.left);
        int right = arrowLength(node.right);

        // 相同节点的算法, 即当前根节点跟子节点如果路径上值相同则累加
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        // 每次最多可以记录1
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }
}
