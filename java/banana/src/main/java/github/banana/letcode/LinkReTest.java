package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

public class LinkReTest {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);

//        PrintListNode.print(listNode);

//        PrintListNode.print(rel(listNode));

        PrintListNode.print(rev2(listNode, 2, 4));
    }

    /**
     * 反正链表 注意备份节点的表达方式即可
     *
     * @param head 原始链表的头结点
     * @return 新链表的头结点
     */
    public static ListNode rel(ListNode head) {
        // 新链表的节点
        ListNode node = null;

        ListNode cur = head;

        while (cur != null) {
            // 备份原始链表的节点引用关系, 后续其它地方引用发生改变但这里是历史备份
            ListNode temp = cur.next;

            // 头插法就是每次将当前节点做头, 头响应的成为它的下一个节点
            cur.next = node;
            // 注意更新头, 当前节点为头
            node = cur;

            cur = temp;
        }

        // 最后的节点置换也是最开始的头节点
        return node;
    }

    /**
     * 反转链表的一段
     * <p>
     * 需要注意几个关键节点
     * 1. 反转前的头结点, 相当于头插法进行逆转
     * 2. 需要逆转的第一个节点和最后一个节点
     * 3. 头结点是否需要处理
     *
     * @param head 原始链表头
     * @param m    逆转的第一个节点
     * @param n    逆转的最后一个节点
     * @return 完成逆转后的新链表的头结点
     */
    public static ListNode rev2(ListNode head, int m, int n) {
        // 保存最终链表
        ListNode result = head;

        int length = n - m + 1;

        // 找到待反转的新的头结点
        ListNode preHead = null;
        while (head != null && --m > 0) {
            preHead = head;
            head = head.next;
        }

        // 此时的链表节点即为新的反转头结点
        // 记录第一个节点的位置即为反转完毕后的最后一个节点
        ListNode tail = head;

        // 开始反转, 反转的套路一致
        ListNode newHead = null;
        while (head != null && length > 0) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;

            head = next;
            length--;
        }

        // 尾节点有效则需要连接剩下的节点
        if (tail != null) {
            tail.next = head;
        }

        // 是否操作了头结点
        if (preHead != null) {
            preHead.next = newHead;
        } else {
            result = newHead;
        }

        return result;
    }
}
