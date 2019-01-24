package github.banana.letcode;

/**
 * 给出两个非空的链表用来表示两个非负的整数
 * 其中, 它们各自的位数是按照逆序的方式存储的, 并且它们的每个节点只能存储一位数字
 * 如果, 我们将这两个数相加起来, 则会返回一个新的链表来表示它们的和
 * 您可以假设除了数字 0 之外, 这两个数都不会以 0 开头
 *
 * @link https://leetcode-cn.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {

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

    /**
     * 就像你在纸上计算两个数字的和那样, 我们首先从最低有效位也就是列表 l1 和 l2 的表头开始相加
     * 由于每位数字都应当处于 0…9 的范围内, 我们计算两个数字的和时可能会出现“溢出”
     * 例如, 5 + 7 = 12, 在这种情况下, 我们会将当前位的数值设置为 2, 并将进位 carry=1 带入下一次迭代
     * 进位 carry 必定是 0 或 1, 这是因为两个数字相加(考虑到进位)可能出现的最大和为 9 + 9 + 1 = 19
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = l1, q = l2, curr = head;
        int carry = 0;
        while (p != null || q != null) {
            int x = p != null ? p.val : 0;
            int y = q != null ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return head.next;
    }

    /**
     * 计算一个链表的和
     *
     * @param listNode 链表
     * @return 和
     */
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
