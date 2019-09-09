package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

public class MergeTwoListsV2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        PrintListNode.print(mergeTwoLists(l1, l2));
    }

    // 新链表节点依次拼接即可, 连接的有序数组合并比数组方便, 但是效率没有数组好, 数组可以从尾部依次合并
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(-1);

        // 用一个临时节点来依次拼接两个链表
        ListNode temp = node;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        // 可能会有一个链表剩下, 剩下的直接拼接到末尾
        if (l1 != null) {
            temp.next = l1;
        }
        if (l2 != null) {
            temp.next = l2;
        }

        return node.next;
    }
}
