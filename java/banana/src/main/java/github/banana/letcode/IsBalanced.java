package github.banana.letcode;

import github.banana.common.TreeNode;

/**
 * 110. 平衡二叉树
 * <p>
 * 给定一个二叉树，判断它是否是高度平衡的二叉树
 * <p>
 * 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1
 * <p>
 * 通过访问所有的节点, 判断以该节点为头节点的子树是否为平衡二叉树
 * 如何判断以该节点为头节点的子树是否为平衡二叉树呢?
 * 首先判读该节点的左子树和右子树是否为平衡二叉树, 如果左子树和右子树都为平衡二叉树
 * 然后再比较其左子树和右子树的高度差
 * 注意, 只要这整个二叉树中有一个节点其左子树或者右子树不是平衡二叉树, 那么这整个二叉树就不是平衡二叉树
 * <p>
 * 以该节点为头节点的子树为平衡二叉树的条件:
 * 1. 该节点的左右子树都为平衡二叉树
 * 2. 左子树, 右子树的高度差必须小于等于1
 * <p>
 * 我们通过递归函数来实现这一过程
 * 如果我们要判断以当前节点为开头的子树是否为平衡二叉树, 我们应该得到当前节点的左子树和右子树的哪些信息
 * 1. 当前节点的左子树是否为平衡二叉树(判断左子树是否为平衡二叉树时要用到)
 * 2. 当前节点的右子树是否为平衡二叉树(判断右子数是否为平衡二叉树时要用到)
 * 3. 当前节点的左子树的高度
 * 4. 当前节点的右子树的高度(3, 4要在比较左子树和右子数的高度差时要用到)
 * <p>
 * 通过上述可知, 我们递归函数的返回值应该返回两个信息
 * 1. 该子树是否为平衡二叉树
 * 2. 该子树的高度
 * <p>
 * 树的高度是边数
 * <p>
 * 层才是高度 + 1
 *
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15  7
 * </pre>
 * <p>
 * 树的高度的计算
 */
public class IsBalanced {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        isBalanced(node);
    }

    public static boolean isBalanced(TreeNode root) {
        return !(depth(root) == -1);
    }

    private static int depth(TreeNode node) {
        // null 节点的高度是0
        if (node == null) {
            return 0;
        }

        // 每个节点返回的是不是平衡二叉树或者高度
        int left = depth(node.left);
        int right = depth(node.right);

        // 如果有一边的节点已经高度差大于1则无需递归
        if (left == -1 || right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        // 高度的计算, 其实计算为节点个数了, 也没问题
        return left > right ? left + 1 : right + 1;
    }
}
