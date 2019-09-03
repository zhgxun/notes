package github.banana.letcode;

import github.banana.common.ListNode;

public class AddTwoNumbersV2 {

    // 直接计算应该是效率最高的解法
    // 如果将链表转为字符串在转为数字进行求和, 再将字符串转为链表也可以, 虽然容易理解但是似乎比较笨
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 新链表来存储目标值
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        // 两个链表都遍历完毕
        while (p != null || q != null) {
            // 第一个链表的值, 无事默认为0, 因为加法0不影响值
            int x = (p != null) ? p.val : 0;
            // 第二个链表的值
            int y = (q != null) ? q.val : 0;
            // 求这一位的和, 注意进位增加只能为0或者1, 因为已经在对应的进制位上不需要对应的倍数了
            int sum = carry + x + y;
            // 看模数, 这个要知道, 10以内的取模为0, 10以上的取模为1, 因为最大只能到19, 20/10=2 19/10=9
            carry = sum / 10;
            // 当前位存储, 注意次数为取余, 10以内的为本身, 10-19为余数, 进制已被提前计算好无需关心
            curr.next = new ListNode(sum % 10);
            // 记录下一个待写入的链表位置
            curr = curr.next;
            // 移动链表直到遍历完毕
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }

        // 最后如果还有多余的余数, 需要补在最后面, 即最大的进制位
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        // 返回链表头
        return dummyHead.next;
    }
}
