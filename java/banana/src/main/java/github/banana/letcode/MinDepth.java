package github.banana.letcode;

import github.banana.common.TreeNode;

/**
 * 111. 二叉树最小深度
 * <p>
 * 对于该二叉树
 * <pre>
 *        1
 *         \
 *         2
 *          \
 *          3
 * </pre>
 * 最大深度和最小深度都是3, 这个不要理解错误, 否则会算错
 * <p>
 * 我们来做一个简单的递归分析, 就知道最小深度和最大深度的区别, 最大深度是取最大值来表示层数, 最小深度却发觉不可以
 * <p>
 * 对于这个二叉树的最小深度, 我们尝试利用 H = min(left, right) + 1 来递归
 * <p>
 * 正推时:
 * <p>
 * H = 1 + min (H(2-tree), 0)
 * H(2-tree) = 1 + min(H(3-tree), 0)
 * H(3-tree) = 1 + min(0, 0) = 1 + 0 = 1
 * <p>
 * 此时得到 H(3-tree) = 1 开始反推, 类似递归的往外层回归
 * H(2-tree) = 1 + min(H(3-tree), 0) = 1 + min(1, 0) = 1 + 0 = 1
 * H = 1 + min(H(2-tree), 0) = 1 + min(1, 0) = 1 + 0 = 1
 * <p>
 * 看到了吧, 最后退出来最小深度却是1, 这里有个问题就是求最小值时我们发觉, 其实是有一个节点即一层深度的, 但是却不能取最小值, 应该记录深度
 * <p>
 * 所以最小深度需要对空节点特殊处理, 即不能取最小, 由于我们不知道是左叶子节点还是右叶子节点, 但都是一个为0, 一个为1, 故直接相加即可
 * <p>
 * 我们再倒推一次
 * 需要保证这个判断: left > 0 && right > 0 ? (1 + min(left, right)) : (1 + left + right);
 * H(3-tree) = 1 + 0 + 0 = 1
 * H(2-tree) = 1 + 1 = 2
 * H = 1 + 2 = 3
 * <p>
 * 这点需要注意比较坑, 这也是计算机的奇妙之处, 很多东西理解和设计都比较抽象, 需要推演和记忆才能很好的处理出预期的结果
 */
public class MinDepth {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = null;
        node.left.left = new TreeNode(3);

        System.out.println(minDepth(node));
    }

    // 二叉树最大深度为根节点到最远叶子节点的位置
    // 二叉树最小深度为根节点到最近叶子节点的位置
    // 最大深度不需要考虑空节点
    // 最小深度需要考虑空节点
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);

        return (left > 0 && right > 0) ? (Math.min(left, right) + 1) : (left + right + 1);
    }
}
