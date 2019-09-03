package github.banana.letcode;

import github.banana.common.ListNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteDuplicates {

    public static void main(String[] args) {
        // 1 2 3 3 4 4 5
        ListNode node = new ListNode(1);
//        node.next = new ListNode(2);
//        node.next.next = new ListNode(3);
//        node.next.next.next = new ListNode(3);
//        node.next.next.next.next = new ListNode(4);
//        node.next.next.next.next.next = new ListNode(4);
//        node.next.next.next.next.next.next = new ListNode(5);

        node.next = new ListNode(1);
        node.next.next = new ListNode(1);
        node.next.next.next = new ListNode(1);
        node.next.next.next.next = new ListNode(2);
        node.next.next.next.next.next = new ListNode(3);

        ListNode node2 = node;
        System.out.println("原始链表...");
        while (node2 != null) {
            System.out.print(node2.val + " ");
            node2 = node2.next;
        }
        System.out.println(" ");

        ListNode node1 = deleteDuplicates2(node);
        System.out.println("删除后的链表...");
        while (node1 != null) {
            System.out.print(node1.val + " ");
            node1 = node1.next;
        }
        System.out.println(" ");
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }

    /**
     * 保存的方式为用一个新的链表来保存无重复的链表
     *
     * @param head 原始链表头
     * @return 新链表头
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }

        // 虚幻的链表头
        ListNode node = new ListNode(-1);

        ListNode slow = head;
        ListNode fast;

        // 记住头指针
        head = node;

        while (slow != null) {
            boolean flag = false;

            // 始终是上一个链表的后一个指针
            fast = slow.next;
            // 找重复值
            while (fast != null && fast.val == slow.val) {
                flag = true;
                // 记录当前的节点
                fast = fast.next;
            }

            // 当前值无重复时直接将链表拼接到 node 上
            // 注意保存的方式
            if (!flag) {
                node.next = slow;
                node = slow;
            }

            // 快指针位置
            slow = fast;
        }

        node.next = null;

        return head.next;
    }
}
