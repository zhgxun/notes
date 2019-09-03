package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

public class RemoveNthFromEndV2 {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        PrintListNode.print(removeNthFromEnd(node, 4));
    }

    /*
     * 原始链表
     * 1 -> 2 -> 3 -> 4 -> 5
     */

    /*
     * 补齐头部防止只有一个元素时异常, 这是链表操作的基本常识, 牢记
     * 0 -> 1 -> 2 -> 3 -> 4 -> 5
     */
    // 这基本是效率最好的时候了
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = new ListNode(0);
        node.next = head;

        ListNode current = head;
        // 计算链表的长度
        int length = 0;
        while (current != null) {
            length++;
            current = current.next;
        }

        // 计算倒数的尾节点在哪里
        length -= n;
        current = node;
        while (length > 0) {
            length--;
            // 找到的节点其实为待删除节点的父节点
            current = current.next;
        }

        // current 为我们关心的节点, 删除它的下一个节点即可
        current.next = current.next.next;

        return node.next;
    }
}
