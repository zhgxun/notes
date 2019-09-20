package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

import java.util.Stack;

public class AddTwoNumbersV3 {

    public static void main(String[] args) {
        ListNode left = new ListNode(1);
//        left.next = new ListNode(2);
//        left.next.next = new ListNode(4);
//        left.next.next.next = new ListNode(3);

        ListNode right = new ListNode(9);
        right.next = new ListNode(9);
        right.next.next = new ListNode(9);

        PrintListNode.print(addTwoNumbers(left, right));
    }

    // 因为用了3个栈故效率不是最高不过已经可以
    // 1563个单元测试耗时10ms
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 空链表处理
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // 借助栈结构来存储数据
        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();

        while (l1 != null) {
            stackA.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stackB.push(l2.val);
            l2 = l2.next;
        }

        // 借助另外一个栈来存储目标元素, 最后在生成链表结构
        Stack<Integer> stack = new Stack<>();

        // 记录进位数
        int remain = 0;

        // 使用任意一个栈做基数, 剩余的均为高位即可
        while (!stackA.isEmpty() && !stackB.isEmpty()) {
            // 总和应为加上进位数后的和, 在用该数进行取余
            int sum = (stackA.pop() + stackB.pop()) + remain;
            // 可能存在的进位数, 不足10时进位数为0
            // 该位置位数真实值
            stack.push(sum % 10);
            // 第一次计算不加进位数后续都需要加上一次操作的进位数
            remain = sum / 10;
        }

        // 如果栈中的元素未能在上一次出栈完毕, 则在高位补
        while (!stackA.isEmpty()) {
            int sum = stackA.pop() + remain;
            stack.push(sum % 10);
            remain = sum / 10;
        }
        while (!stackB.isEmpty()) {
            int sum = stackB.pop() + remain;
            stack.push(sum % 10);
            remain = sum / 10;
        }

        // 有个特殊条件, 只留下有效余数时的处理
        if (remain > 0) {
            stack.push(remain);
        }

        // 最终的目标数据存在目标栈中
        ListNode node = new ListNode(0);
        ListNode head = node;
        while (!stack.isEmpty()) {
            node.next = new ListNode(stack.pop());
            node = node.next;
        }

        return head.next;
    }
}
