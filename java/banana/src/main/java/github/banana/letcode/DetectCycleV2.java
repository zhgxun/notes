package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

import java.util.HashSet;
import java.util.Set;

public class DetectCycleV2 {

    public static void main(String[] args) {
        ListNode node = new ListNode(3);
        node.next = new ListNode(2);
        node.next.next = new ListNode(0);
        node.next.next.next = new ListNode(-4);
        node.next.next.next.next = node.next;
        PrintListNode.print(node);
        System.out.println(detectCycleV2(node));
    }

    // 最常规的解法, 哈希法
    // 需要占用O(n)的空间复杂度
    // 但是效率明显不如双指针法, 双指针法只要记得安全的移动步数是慢指针一次移动一步, 快指针一次移动两步就更有效的解决
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<>();
        while (head != null) {
            // 第一个相同的节点就是入环的节点
            if (nodeSet.contains(head)) {
                return head;
            } else {
                nodeSet.add(head);
            }
            head = head.next;
        }

        return null;
    }

    /**
     * 分析, 理解这段很重要
     *
     * <pre><code>
     * A      B      C
     * 3  ->  2  ->  0  -> 4
     *        ^            |
     *        |-------------
     * </code><pre/>
     * <p>
     * 按两步走, 快慢指针初始值都在 A 处, 每一次移动元素的距离都是相同的
     * 那如果是环形链表, 快慢指针终究会相遇, 相遇的节点为 C, 加入环的入口在 B 处, 那么此时有
     * 慢指针走过的距离 * 2 = 快指针走过的距离, 有距离表示如下
     * 2 (AB + BC) = AB + BC + CB + BC
     * 消除后得知 AB = CB
     * 理解这个很重要, 这样就知道指针从头开始到入环的位置, 居然跟相遇往下再次入环的位置刚好是相等的, 这就厉害了, 哈哈
     * <p>
     * 那解决思路就很简单了, 首先找到相遇点, 不存在则为非环形链表, 否则存在上述推导公式
     * 接下来使用2个每次进一步的指针继续往下走, 相遇处就是 B 点了, 因链表无法找到头结点, 需要一个新的头指向即可
     */
    public static ListNode detectCycleV2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow, fast;
        slow = fast = head;
        do {
            slow = slow.next;
            fast = fast.next.next;
        } while (fast != null && fast.next != null && slow != fast);

        if (fast == null || fast.next == null) {
            return null;
        }

        // 此时只是相遇但是不知道入口节点在哪里, 通过计算得知, 慢指针继续往前走, 一个从头节点开始走的指针会刚好跟它相遇, 还是不容易理解的
        ListNode tmp = head;
        while (tmp != slow) {
            tmp = tmp.next;
            slow = slow.next;
        }
        return tmp;
    }

    // 更容易理解的方式, 均从链表头部开始进行处理
    public static ListNode detectCycleV3(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode point = getIntersect(head);
        if (point == null) {
            return null;
        }

        ListNode temp = head;
        while (temp != point) {
            temp = temp.next;
            point = point.next;
        }

        return temp;
    }

    // 是否是环形链表, 推荐也是需要铭记的写法, 从链表头开始进行处理
    private static ListNode getIntersect(ListNode head) {
        // 快慢指针都从链表头开始
        ListNode slow = head;
        ListNode fast = head;
        // 用快指针来判断是否到达链表末尾
        while (fast != null && fast.next != null) {
            // 慢指针每次前进一步
            slow = slow.next;
            // 快指针每次前进两步
            fast = fast.next.next;
            // 他们会最终相遇, 而且可以用数学推理来证明
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }
}
