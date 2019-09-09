package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

public class RotateRight {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        PrintListNode.print(rotateRight(node, 2));
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // 注意旋转点的位置, 长度内和长度外, 跟长度取余就是从末尾移动的步数
        // 1. 求链表长度
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        // 2. 计算从链表头开始需要移动到待旋转的步数
        int step = length - k % length;
        // 等于链表本身的长度则无需旋转, 比如移动的位置是链表长度的倍数
        if (step == length) {
            return head;
        }

        // 开始处理移动, 不要走到需要移动的首元素
        // 避免需要一个额外的空间记录上一个节点
        ListNode temp = head;
        while (step-- > 1) {
            temp = temp.next;
        }
        // 此时 temp 就是需要移动的第一个节点的上一个节点
        // 比如 1->2->3->4->5 移动2个位置, 此时 temp 是在 3 的位置, 3为新链表的结束节点, 需要将结束节点清空为 null
        // 记录待旋转节点的首节点
        ListNode move = temp.next;
        // 将当前节点的下一个节点设置为结束节点
        temp.next = null;

        // 将待旋转的节点结束节点拼接上之前的开始节点部分
        // 设置新的首节点
        ListNode newHead = move;
        while (move.next != null) {
            move = move.next;
        }
        move.next = head;

        return newHead;
    }
}
