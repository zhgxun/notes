package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

public class OddEvenList {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        PrintListNode.print(oddEvenList(node));
    }

    // 最容易想到的解法, 但是不符合最优的空间复杂度
    // 时间复杂度2O(n), 空间复杂度需要占用两份奇偶链表空间, 总和为O(n)的空间复杂度
    // 题目要求原地O(1)的解法
    // 不过这种是最容易想到的解法
    // 但是效率不高通过71个单元测试用例的时间是3ms
    // 这个解法有更优秀的实现方式, 看下面的第二种解法
    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);

        ListNode tempLeft = left;
        ListNode tempRight = right;
        int i = 1;
        while (head != null) {
            // 偶数节点
            if (i++ % 2 == 0) {
                tempRight.next = new ListNode(head.val);
                tempRight = tempRight.next;
            } else {
                tempLeft.next = new ListNode(head.val);
                tempLeft = tempLeft.next;
            }
            head = head.next;
        }

        // 此时将偶数节点拼接在奇数节点的后面即可
        ListNode temp = left;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = right.next;

        return left.next;
    }

    // 奇偶链表高效的拼接方法
    // 注意奇偶节点的互相变化, 先操作奇数节点, 顺利的下一个元素为偶数节点
    // 这个效率就比较好, 71个测试用例仅需要1ms, 其实新建实例的耗时3ms也比较快了，只是说这样的方式是更高效的操作方式
    // 时间复杂度和空间复杂度当然都是占用越少运行越快效率越高, 但至于效率高到什么程度, 只有在性能敏感的场合才是明显的
    public static ListNode oddEvenListV2(ListNode head) {
        if (head == null) {
            return null;
        }

        // odd 奇数指针, 往后移动
        ListNode odd = head;
        // 迭代开始, 第二个元素为偶数节点
        ListNode even = head.next;
        // evenHead 偶数指针的头, 即第二个元素开始的记录
        ListNode evenHead = even;
        // 往后则奇数节点往奇数指针后面添加, 偶数节点往偶数指针后面添加
        while (even != null && even.next != null) {
            // 第三个元素奇数节点
            odd.next = even.next;
            // 移动结束指针
            odd = odd.next;
            // 奇数指针往后移动下一步则为偶数节点, 拼接到偶数指针后面
            even.next = odd.next;
            // 移动偶数指针, 下一次变更为奇数节点
            even = even.next;
        }

        // 偶数指针拼接到奇数指针后面则构成奇偶链表
        odd.next = evenHead;

        return head;
    }
}
