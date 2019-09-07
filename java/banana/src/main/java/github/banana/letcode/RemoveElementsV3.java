package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

public class RemoveElementsV3 {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(6);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(6);
        PrintListNode.print(removeElementsV2(node, 6));
    }

    // 新链表拼接的方式, 有效元素直接添加到链表中
    // 每次实例化一个元素节点, 显然占用额外的空间复杂度, 效率自然一般, 不过应该是最容易想到的解法
    // 而且这不是链表删除的解法, 链表删除是寻找直接替换的下一个节点
    public static ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode(0);
        ListNode temp = node;

        while (head != null) {
            if (head.val != val) {
                // 注意这里需要使用新的元素节点
                temp.next = new ListNode(head.val);
                temp = temp.next;
            }
            head = head.next;
        }

        return node.next;
    }

    // 对上一种解法的优化, 使用快慢指针法来寻找下一个可进行交换的节点
    // 其实看起来两种解法差异不大, 但是快慢指针效率就特别高
    // 其实也就差别在1ms时间内
    // 这也是开发中经常提及的, 保留易读容易理解胜过执行效率, 除非是在性能敏感的场合, 否则易用易理解始终是属于第一位的
    public static ListNode removeElementsV2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head.val == val ? null : head;
        }

        ListNode node = new ListNode(0);
        node.next = head;

        // 注意赋值要为头结点才可以, 如果赋值其它的改变不了链表
        ListNode slow = node, fast;
        while (slow != null && slow.next != null) {
            // 目标值所在的节点需要删除
            if (slow.next.val == val) {
                // 通过快速指针去寻找下一个不相同的元素为止
                fast = slow.next.next;
                while (fast != null && fast.val == val) {
                    fast = fast.next;
                }
                // 当前的fast节点是有效的

                // 丢弃不需要的节点, 直接拼接上需要的节点
                slow.next = fast;
            }
            slow = slow.next;
        }

        return node.next;
    }
}
