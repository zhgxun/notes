package github.banana.letcode;

/**
 * 21. 合并两个有序链表
 * <p>
 * 将两个有序链表合并为一个新的有序链表并返回, 新链表是通过拼接给定的两个链表的所有节点组成的
 * <p>
 * 思路可以用一个链表作为基准, 另一个链表往里插入数据, 但是插入数据的位置是不确定的, 可能是头也可能是尾
 * <p>
 * 还有更简单的方法就是新建一个链表, 逐渐往下构建元素即可, 这种方法比较简单就是空间需要占用
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode node = null;
        ListNode node1 = new ListNode(1);

        ListNode node2 = mergeTwoLists(node, node1);
        while (node2 != null) {
            System.out.println(node2.val);
            node2 = node2.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 只存在一个链表时
        if (l1 == null && l2 != null) {
            return l2;
        }
        if (l2 == null && l1 != null) {
            return l1;
        }

        // 操作方式是将两个链表直接连接到新链表上的思路来完成
        ListNode dummy = new ListNode(0);

        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            // 看题目的意思是升序排序了
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;

            // 任意一个节点为空则直接补齐即可
            if (l1 == null) {
                cur.next = l2;
            } else {
                cur.next = l1;
            }
        }

        return dummy.next;
    }
}
