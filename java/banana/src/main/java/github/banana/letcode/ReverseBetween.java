package github.banana.letcode;

/**
 * 反转从位置 m 到 n 的链表, 请使用一趟扫描完成反转
 * <p>
 * 1 ≤ m ≤ n ≤ 链表长度
 */
public class ReverseBetween {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        ListNode root = reverseBetween(node, 2, 4);
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.next;
        }
        System.out.println(" ");
    }

    // 1->2->3->4->5->NULL, m = 2, n = 4
    // 1->4->3->2->5->NULL
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        // 找到真正需要处理的头结点 pre, 位于 m 处
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }

        ListNode p = pre.next;
        ListNode temp;
        for (int i = 0; i < n - m; i++) {
            /*
             * 注意交换顺序, 链表最恶心的就是这玩意
             *
             *   pre   p   temp
             * -> 1 -> 2 -> 3 -> 4 -> 5 ->
             *         |         |
             *         -----------
             * 当前情况描述就是 需要对链表 2 -> 3 -> 4 进行反转为 4 -> 3 -> 2
             * 而链表反转的方法就是头节点插入法
             * 因此首先找到头节点, 此处的 pre 节点
             *
             * 交换的过程周容易丢失指针, 这里总结就是对于一个位置
             *
             * 头节点 当前节点 和下一个临时节点
             *  -> pre -> p -> temp -> source
             *
             *  目标是将 temp 与 p 交换 但是不能丢失对 source 的引用
             *
             *  交换规则为:
             *  1. 临时保存当前节点 p 的下一个节点 temp = p.next
             *  2. 当前节点 p 的下一个节点指向原始 source 的引用 p.next = p.next.next
             *  3. 临时节点 temp 的下一个节点引用更新为头结点的下一个元素 temp.next = pre.next
             *  4. 最后更新头结点的下一个引用 pre.next = temp
             */
            temp = p.next;
            p.next = p.next.next;
            temp.next = pre.next;
            pre.next = temp;
        }

        return dummy.next;
    }
}
