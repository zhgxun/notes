package github.banana.letcode;

import github.banana.common.TreeNode;

/**
 * 相同的树
 * <p>
 * 如果两个树在结构上相同, 并且节点具有相同的值, 则认为它们是相同的
 */
public class IsSameTree {

    public static void main(String[] args) {

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // all is null
        if (p == null && q == null) {
            return true;
        }
        // 根节点值必须相同再比较
        if (p != null && q != null && p.val == q.val) {
            // 这个递归用的漂亮
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }
}
