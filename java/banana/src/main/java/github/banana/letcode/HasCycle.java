package github.banana.letcode;

import github.banana.common.ListNode;

/**
 * 给定一个链表, 判断链表中是否有环
 */
public class HasCycle {

    public static void main(String[] args) {

    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head, fast = head.next;
        // 两个节点最终相遇即相等不再循环则存在环
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
