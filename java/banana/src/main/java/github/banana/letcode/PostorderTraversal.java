package github.banana.letcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 145. 二叉树的后序遍历
 * <p>
 * 给定一个二叉树, 返回它的 后序 遍历
 *
 * <pre>
 *    1
 *     \
 *      2
 *     /
 *    3
 * </pre>
 * <p>
 * [3, 2, 1]
 */
public class PostorderTraversal {

    public static void main(String[] args) {

    }

    private List<Integer> list = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }

        if (root.left != null) {
            postorderTraversal(root.left);
        }
        if (root.right != null) {
            postorderTraversal(root.right);
        }

        list.add(root.val);
        return list;
    }
}
