package com.github.zhgxun.learn.notes.leetcode;

import com.github.zhgxun.learn.notes.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 面试题07. 重建二叉树
 * <p>
 * 这个题个人觉得还是挺难的
 * <p>
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 */
public class BuildTree {

    /**
     * @param preorder 前序遍历结果
     * @param inorder  中序遍历结果
     * @return 重建回二叉树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        // 存储中序遍历的元素
        // 键为元素值为索引
        Map<Integer, Integer> indexMap = new HashMap<>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }

        return buildTree(
                preorder,
                0,
                length - 1,
                0,
                length - 1,
                indexMap
        );
    }

    /**
     * 根据前序遍历元素列表和中序遍历元素列表重建二叉树
     *
     * @param preorder      前序遍历元素列表
     * @param preorderStart 前序遍历元素当前索引
     * @param preorderEnd   前序遍历结束索引
     * @param inorderStart  中序遍历当前索引
     * @param inorderEnd    中序遍历结束索引
     * @param indexMap      中序遍历元素映射表
     * @return 重建后的树
     */
    public TreeNode buildTree(
            int[] preorder,
            int preorderStart,
            int preorderEnd,
            int inorderStart,
            int inorderEnd,
            Map<Integer, Integer> indexMap
    ) {
        // 前序遍历超过元素个数无需处理树
        if (preorderStart > preorderEnd) {
            return null;
        }

        // 每一个前序遍历的元素都是当前位置树的根节点
        int rootVal = preorder[preorderStart];
        // 建树的根
        TreeNode root = new TreeNode(rootVal);

        /*
         * 样例树
         * <pre>
         *     3
         *     /\
         *    9 20
         *      /\
         *     15 7
         * </pre>
         *
         * 前序遍历结果, 先打印根节点, 在打印左子节点, 最后打印右子节点
         * [3, 9, 20, 15, 7]
         * 中序遍历结果, 先打印左子节点, 在打印根节点, 最后打印右子节点
         * [9, 3, 15, 20, 7]
         */

        // 说明此事前序遍历索引中间有节点需要处理
        if (preorderStart != preorderEnd) {

            // 当前根元素在中序遍历中的位置
            int rootIndex = indexMap.get(rootVal);

            // 左节点的位置, 当前根元素在中序遍历中的位置左边的元素都是当前位置的左树节点
            int leftNodes = rootIndex - inorderStart;
            // 右节点的位置, 中序遍历的结束减去当前根元素在中序遍历中的索引之间的元素都是右子树
            int rightNodes = inorderEnd - rootIndex;

            TreeNode leftSubtree = buildTree(
                    preorder,
                    preorderStart + 1,
                    preorderStart + leftNodes,
                    inorderStart,
                    rootIndex - 1,
                    indexMap
            );

            TreeNode rightSubtree = buildTree(
                    preorder,
                    preorderEnd - rightNodes + 1,
                    preorderEnd,
                    rootIndex + 1,
                    inorderEnd,
                    indexMap
            );

            root.left = leftSubtree;
            root.right = rightSubtree;
        }

        return root;
    }

    // 迭代法
    public TreeNode buildTreeV2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        int length = preorder.length;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < length; i++) {
            // 依次取出前序遍历的元素
            int preorderVal = preorder[i];

            /*
             * 比如这棵树
             * <pre>
             *         3
             *        / \
             *       9  20
             *      /  /  \
             *     8  15   7
             *    / \
             *   5  10
             *  /
             * 4
             * </pre>
             * 前序遍历: preorder = [3,9,8,5,4,10,20,15,7]
             * 中序遍历: inorder = [4,5,8,10,9,3,15,20,7]
             *
             * 有一些特点需要留意
             * 前序遍历的第一个元素为树的根节点, 后面的元素依次为子树的根节点, 至于根节点是左子树还是右子树, 仅仅看前序遍历是无法完全确定的
             * 中序遍历的第一个元素一定是左子树节点
             * 因此从前序遍历一直往后找到跟中序遍历第一个相同的元素则找到这棵树的最左边
             */

            // 窥探上一个节点
            TreeNode node = stack.peek();

            // 如果跟中序遍历节点不相同则始终在左子树上
            if (node.val != inorder[inorderIndex]) {
                // 因此依次追加到当一个节点的左子树上即可
                node.left = new TreeNode(preorderVal);
                // 同时记录当前节点的备份
                stack.push(node.left);
            } else {
                // 如果上一个节点跟当前中序遍历的节点相同则说明左子树应处理完毕
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                // 该前序遍历的值为上一次出栈的节点的右子节点
                // 栈为空则说明节点都在右子树上直接拼接即可
                node.right = new TreeNode(preorderVal);
                // 并将当前处理的节点压栈
                stack.push(node.right);
            }
        }

        return root;
    }
}
