package github.banana.letcode;

/**
 * 链表的中间节点
 */
public class MiddleNode {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        System.out.println(node.next.val);
        System.out.println(node.next.next);
    }

    public ListNode middleNode(ListNode head) {
        // 找链表的中点位置, 快慢指针
        // slow 慢指针只是用来记录当前数据的位置, slow 平步移动
        // fast 快指针是为了探寻链表的结尾, fast 两倍移动
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            // null 也可以继续下移, 但是无意义
            fast = fast.next.next;
        }

        return slow;
    }
}
