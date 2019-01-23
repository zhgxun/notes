package github.banana.letcode;

public class Solution {

    public static void main(String[] args) {
        // 定义一个链表, 逆序记录数字 2，4，3 , 实际表示数字 342
        ListNode listNode1 = new ListNode(2);
        listNode1.next = new ListNode(4);
        listNode1.next.next = new ListNode(3);

        // 定义链表, 实际数字 465
        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(4);

        ListNode node = addTwoNumbers(listNode1, listNode2);
        System.out.println("Total: " + total(node));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = total(l1) + total(l2);
        // 借助字符串长度得知结果和的数字位数
        String number = String.valueOf(sum);
        int length = number.length();
        ListNode node = new ListNode(number.charAt(length - 1) - (int) ('0'));
        // 按字符串的方式获取位数, 并赋值即可
        ListNode curr = node;
        for (int i = length - 2; i >= 0; i--) {
            curr.next = new ListNode(number.charAt(i) - (int) ('0'));
            curr = curr.next;
        }

        return node;
    }

    public static int total(ListNode listNode) {
        ListNode node = listNode;
        int value = listNode.val;
        int base = 10;
        while (node.next != null) {
            node = node.next;
            value = value + node.val * base;
            base *= 10;
        }

        return value;
    }
}

/**
 * 表示一个单链表
 */
class ListNode {
    public int val;

    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
