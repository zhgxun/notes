package github.banana.letcode;

import github.banana.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class GetIntersectionNodeV2 {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(3);
        ListNode b = new ListNode(4);
        b.next = new ListNode(5);
        getIntersectionNodeV2(a, b);
    }

    // O(n)空间复杂度用哈希法解决, 2O(n)时间复杂度
    // 但是运行效率不高, 因为必须遍历和占用额外的空间
    // 优点是非常容易想到解法
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<ListNode> nodeSet = new HashSet<>();
        while (headA != null) {
            nodeSet.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (nodeSet.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }

        return null;
    }

    // 这个解法也比较常规, 算是对哈希算法的优化, 但是边界条件有点多, 需要注意, 不过面试中说明意思即可, 应该宽容一些边界的判断, 即便是弄错
    // 先通过2次遍历, 分别知道两个链表的长度
    // 有了链表长度后, 就需要注意如何找相交点, 我们稍加思考就能想到快慢指针是没办法控制的, 那应该有别的办法
    // 就是让长的链表先走完超出长度的部分, 在一起走, 相等处不就是相交点了吗, 是吧
    // 此时执行效率已经相对比较优秀了
    // 还有更强悍的, 抽象成循环链表来解决, 互相赋值去查找简直是脑筋急转弯一样的, 不轻易能理解
    public static ListNode getIntersectionNodeV2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lengthA = 0, lengthB = 0;
        ListNode a = headA, b = headB;
        while (a != null) {
            lengthA++;
            a = a.next;
        }
        while (b != null) {
            lengthB++;
            b = b.next;
        }

        // 记录遍历完毕的元素个数
        int count = 0;
        // 让长的元素先遍历完超过长度的部分在一起遍历
        while (headA != null && headB != null && headA != headB) {
            if (lengthA > lengthB && count < lengthA - lengthB) {
                // 链表a比较长, 让a先遍历完 lengthA - lengthB 个元素
                headA = headA.next;
                count++;
            } else if (lengthA < lengthB && count < lengthB - lengthA) {
                // 链表B比较长, 让b先遍历完 lengthA - lengthB 个元素
                headB = headB.next;
                count++;
            } else {
                // 链表一样长一起遍历即可
                headA = headA.next;
                headB = headB.next;
            }
        }

        return headA;
    }
}
