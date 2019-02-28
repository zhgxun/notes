package github.banana.letcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称二叉树
 * <p>
 * 了解递归和迭代的遍历方法
 * <p>
 * 注意互为镜像的长相
 *
 * <pre>
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * </pre>
 */
public class IsSymmetric {

    public static void main(String[] args) {

    }

    public boolean isSymmetric(TreeNode root) {
        return mirror(root, root);
    }

    /**
     * 递归解法, 即是比较每个节点的值和位置对称
     *
     * @param left  左边
     * @param right 右边
     * @return 比较结果
     */
    private boolean mirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        if (left.val == right.val) {
            // 结合图看互为镜像是左右交叉的
            return mirror(left.left, right.right) && mirror(left.right, right.left);
        } else {
            return false;
        }
    }

    // 迭代法遍历树, 遍历树一般最方便的方式就是借助链表队列来完成
    // 队列结构和栈结构都可以方便的遍历队列
    // 根据场合选择先进先出还是先进后出
    //
    // 打印链表就要用栈来解决
    //
    // 但是对称不涉及先比较谁的问题, 可以一次比较, 队列就可以, 不需要入栈
    // 栈最糟糕的时候会满
    public boolean isSymmetric1(TreeNode root) {
        // 因为树互为镜像, 每次顺序把树的节点放入队列中来进行比较
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            //
            // 以下方式为叶子节点比较
            //
            // 为空则到达叶子节点不需要在比较
            // 也无法在继续放入节点
            if (left == null && right == null) {
                continue;
            }
            // 其中一个到达 null 则树不对称
            if (left == null || right == null) {
                return false;
            }
            //
            // 非叶子节点都具有的值, 值也是树是否对称的重要组成部分
            // 值不相等, 树也不对称
            //
            if (left.val != right.val) {
                return false;
            }

            // 继续入队比较
            // 入队的方式为 按照对称方式入队, 因为出队是按顺序出队的
            // 可以理解为出队就是一个个的节点
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }

        return true;
    }
}
