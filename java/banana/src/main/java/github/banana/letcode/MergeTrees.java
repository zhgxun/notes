package github.banana.letcode;

/**
 * 617. 合并二叉树
 * <p>
 * 给定两个二叉树, 想象当你将它们中的一个覆盖到另一个上时, 两个二叉树的一些节点便会重叠
 * <p>
 * 你需要将他们合并为一个新的二叉树
 * 合并的规则是如果两个节点重叠, 那么将他们的值相加作为节点合并后的新值, 否则不为 NULL 的节点将直接作为新二叉树的节点
 *
 * <pre>
 * 合并前
 *     	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 合并后
 *     	 3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 * </pre>
 */
public class MergeTrees {

    public static void main(String[] args) {

    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        // 上面如果有一棵树为空, 则已经直接返回, 此时均不为空, 合并根节点的值
        // 将 t2 树覆盖到 t1 树上
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }
}
