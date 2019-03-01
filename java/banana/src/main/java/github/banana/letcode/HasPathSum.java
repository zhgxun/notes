package github.banana.letcode;

/**
 * 112. 路径总和
 * <p>
 * 给定一个二叉树和一个目标和, 判断该树中是否存在根节点到叶子节点的路径, 这条路径上所有节点值相加等于目标和
 * <p>
 * 给定如下二叉树, 以及目标和 sum = 22
 *
 * <pre>
 *           5
 *          / \
 *         4   8
 *        /   / \
 *       11  13  4
 *      /  \      \
 *     7    2      1
 * </pre>
 * <p>
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2
 */
public class HasPathSum {

    public static void main(String[] args) {

    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        // 注意这个退出递归的条件, 是到叶子节点相等, 意味着不能提前终止, 哪怕已经超过范围, 而是直到找到一个符合条件的路径为止
        // 必须走到叶子节点
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
