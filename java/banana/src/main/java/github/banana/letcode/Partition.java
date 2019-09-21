package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

public class Partition {

    public static void main(String[] args) {
        ListNode node = new ListNode(3);
        node.next = new ListNode(4);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(2);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(2);
        PrintListNode.print(partition(node, 3));
    }

    /**
     * 1. 初始化两个指针 before 和 after, 在实现中, 我们将两个指针初始化为哑 ListNode, 这有助于减少条件判断
     * 2. 利用 head 指针遍历原链表
     * 3. 若 head 指针指向的元素值小于 x, 该节点应当是 before 链表的一部分, 因此我们将其移到 before 中
     * 4. 否则该节点应当是 after 链表的一部分, 因此我们将其移到 after 中
     * 5. 遍历完原有链表的全部元素之后, 我们得到了两个链表 before 和 after, 原有链表的元素或者在before 中或者在 after 中, 这取决于它们的值
     * 6. 最后可以将 before 和 after 连接, 组成所求的链表
     *
     * @param head 头指针
     * @param x    目标值
     * @return 分隔后的新链表
     */
    public static ListNode partition(ListNode head, int x) {
        ListNode beforeHead = new ListNode(0);
        ListNode before = beforeHead;
        ListNode afterHead = new ListNode(0);
        ListNode after = afterHead;

        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null;

        before.next = afterHead.next;

        return beforeHead.next;
    }
}
