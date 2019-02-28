package github.banana.letcode;

/**
 * 给定一个链表, 返回链表开始入环的第一个节点
 * 如果链表无环, 则返回 null
 * <p>
 * 看着简单其实也挺不容易, 单纯靠想如果没思考过确实有点绕
 * <p>
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
public class DetectCycle {

    public static void main(String[] args) {

    }

    public ListNode detectCycle(ListNode head) {
        /*
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
        if (head == null || head.next == null) {
            return null;
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

        ListNode tmp = head;
        while (tmp != slow) {
            tmp = tmp.next;
            slow = slow.next;
        }
        return tmp;
    }
}
