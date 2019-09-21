package github.banana.letcode;

import github.banana.common.TreeNode;

/**
 * 108. 将有序数组转换为二叉搜索树
 * <p>
 * 将一个按照升序排列的有序数组, 转换为一棵高度平衡二叉搜索树
 * <p>
 * 一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1
 * <p>
 * 只需要满足一种可能的平衡二叉树即可, 那就按中间来两边建树, 直到为null停止
 * <p>
 * 主要就两个步骤
 * 建父节点和子节点的操作, 而每个子节点又是下一层次的父节点, 需要注意元素索引相同时将该节点直接建立在上一个节点的子节点上即可
 */
public class SortedArrayToBST {

    public static void main(String[] args) {

    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return buildTree(nums, 0, nums.length - 1);
    }

    /**
     * 数组建树过程
     *
     * @param nums  待建树的原始数组
     * @param left  数组左边起始位置
     * @param right 数组右边结束位置
     * @return 建树结果
     */
    private TreeNode buildTree(int[] nums, int left, int right) {
        // 左边的索引不可以大于右边, 否则元素不均等, 建树过程没控制的话无法保证是平衡二叉树
        if (left > right) {
            return null;
        }
        // 数组索引相等是什么梗, 我看看
        // 即是相同元素, 比如
        /**
         * |----------------
         * | 1 | 2 | 3 | 4 |
         * |----------------
         */
        // 中点对应 2 后续直接将当前节点建在父节点的叶子接点上即可
        if (left == right) {
            return new TreeNode(nums[left]);
        }

        // 从中点开始建树
        int mid = (left + right) / 2;

        // 每一次建树的定点是当前的重点值, 然后往两边建树
        TreeNode root = new TreeNode(nums[mid]);
        // 左子树 0 - mid - 1 开始建树 因为中间已经被建成了父节点
        root.left = buildTree(nums, left, mid - 1);
        // 右子树 mid + 1 开始建树
        root.right = buildTree(nums, mid + 1, right);
        return root;
    }
}
