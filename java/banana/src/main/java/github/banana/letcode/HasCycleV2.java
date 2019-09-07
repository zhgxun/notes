package github.banana.letcode;

import github.banana.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class HasCycleV2 {

    // 双指针解法属于比较优秀的解法
    // 但是为啥会快指针移动两步就能相遇这个很难理解, 至少我理解起来就很费劲似懂非懂的
    // 不过这个找循环链表的循环点不方便
    public boolean hasCycle(ListNode head) {
        // 空链表或者单元素链表不存在环
        if (head == null || head.next == null) {
            return false;
        }

        // 快慢指针, 如果存在环则快的指针一定能更慢的指针在还上相遇
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != slow) {
            // 如果走到末尾则没有还
            // 链表不存在环则快指针会先到结尾
            if (fast == null || fast.next == null) {
                return false;
            }
            // 快指针一次走2步
            fast = fast.next.next;
            // 慢指针一步步遍历
            slow = slow.next;
        }

        return true;
    }

    // 但是最常用的解法还是哈希解法, 更容易理解避开双指针特殊的移动步伐
    // 时间复杂度为O(n)遍历一次, 空间复杂度为O(n)因为要拷贝一份原始链表
    // 遍历所有结点并在哈希表中存储每个结点的引用(或内存地址)
    // 如果当前结点为空结点 null(即已检测到链表尾部的下一个结点)
    // 那么我们已经遍历完整个链表, 并且该链表不是环形链表, 因为环形链表永远没有结尾
    // 有点是能直接找到循环链表的循环点
    public boolean hasCycleV2(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<>();
        while (head != null) {
            if (nodeSet.contains(head)) {
                return true;
            } else {
                nodeSet.add(head);
            }

            head = head.next;
        }

        return false;
    }
}
