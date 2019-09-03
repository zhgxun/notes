package github.banana.letcode;

import github.banana.common.ListNode;

/**
 * 160. 相交链表
 * <p>
 * 编写一个程序, 找到两个单链表相交的起始节点
 * <p>
 * 中规中矩的解法见 {@link GetIntersectionNode#getIntersectionNode2(ListNode, ListNode)}
 * <p>
 * 这个解法比较新奇, 见 {@link GetIntersectionNode#getIntersectionNode(ListNode, ListNode)}
 * 假设, 链表A相交前有m个结点, B相交前有n个结点, 两链表相交后至末尾有k个结点
 * 指针从A链表头开始, 向后加至A末尾, 再指向B链表头, 向后加至首个相交结点处, 共走了m+k+n步
 * 同理, 指针从B链表投开始, 到最后相交, 共走了n+k+m步, 和从A出发的指针所走步数一致
 * 因此, 若A链表和B链表相交, 则这样走当两指针相等时必然是在首个相交结点处
 * 若A链表和B链表不相交, 则两指针会在走了m+n步后同时走到对方链表末尾, 均变为NULL
 */
public class GetIntersectionNode {

    public static void main(String[] args) {
//        //
//        ListNode node1 = new ListNode(8);
//        ListNode node2 = new ListNode(4);
//        ListNode node3 = new ListNode(5);
//        //
//
//        ListNode nodeA = new ListNode(4);
//        nodeA.next = new ListNode(1);
//        nodeA.next.next = node1;
//        nodeA.next.next.next = node2;
//        nodeA.next.next.next.next = node3;
//
//        ListNode nodeB = new ListNode(5);
//        nodeB.next = new ListNode(0);
//        nodeB.next.next = new ListNode(1);
//        nodeB.next.next.next = node1;
//        nodeB.next.next.next.next = node2;
//        nodeB.next.next.next.next.next = node3;

        ListNode node1 = new ListNode(2);
        ListNode nodeA = new ListNode(1);
        nodeA.next = node1;

        ListNode nodeB = node1;

        ListNode node = new GetIntersectionNode().getIntersectionNode2(nodeA, nodeB);
        if (node != null) {
            System.out.println(node.val);
        } else {
            System.out.println("Null");
        }
    }

    // 这个解法比较烧脑, 需要有一定的推断
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;

        // b
        while (nodeA != nodeB) {
            if (nodeA == null) {
                nodeA = nodeB.next;
            } else {
                nodeA = headB;
            }

            if (nodeB == null) {
                nodeB = headA;
            } else {
                nodeB = headA;
            }
        }

        return nodeA;
    }

    // 这个解法比较常规, 中规中矩
    // 找到链表长度差, 让较长的链表先走完超出的长度
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode a = headA, b = headB;

        // 求链表的长度, 找出相差的点
        // 链表的长度为中间的连接线
        int m = 0, n = 0;
        while (a.next != null) {
            a = a.next;
            m++;
        }

        while (b.next != null) {
            b = b.next;
            n++;
        }

        // 相交链表的最后一个元素必然相等
        if (a != b) {
            return null;
        }

        /*
         * |--------- m ----------|
         *
         * |-- a ----|----- c ----|
         * a1 -> a2
         *         \
         *           c1 -> c2 -> c3
         * b1 -> b2 /
         *  |-- b ---|----- c ----|
         * |--------- n ----------|
         *
         * m = a + c
         * n = b + c
         *
         * m - n = a + c - b - c = a - b
         *
         * 经过两次遍历后, m - n 为一个常量, 就是 a 和 b 之间相差的元素个数, 让较长的元素先走完超过长度的元素个数在一起前进
         * 相等处即为相交的点
         */
        // 链表比较长, 让长链表先走完 m - n 个元素
        int count = 0;
        // 注意这里的条件, 没注意 1 -> 2 和 2 这样的链表
        while (headA != null && headB != null && headA != headB) {
            // 注意 m-n 的值, 可以使用 Math.abs( m - n);
            if (m > n && count < m - n) {
                count++;
                headA = headA.next;
            } else if (count < n - m) {
                count++;
                headB = headB.next;
            } else {
                headA = headA.next;
                headB = headB.next;
            }
        }

        return headA;
    }
}
