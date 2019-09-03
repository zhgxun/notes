package github.banana.letcode;

import github.banana.common.ListNode;

/**
 * 反转单链表
 */
public class ReverseList {

    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            // 进行变换
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 新链表头节点插入法
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = new ListNode(-1);

        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;

            // 即将把当前节点添加到新链表的头节点, 顺带将新链表已有头结点附加在当前待变成
            // 头节点的子节点
            cur.next = node.next;

            // 将 node 的头节点更新为当前节点
            node.next = cur;

            // 继续遍历原始链表
            cur = next;
        }

        return node.next;
    }
}
