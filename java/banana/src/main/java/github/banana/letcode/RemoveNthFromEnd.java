package github.banana.letcode;

/**
 * 删除链表的倒数第N个节点
 * <p>
 * 我们将添加一个哑结点作为辅助
 * 该结点位于列表头部
 * 哑结点用来简化某些极端情况
 * 例如列表中只含有一个结点
 * 或需要删除列表的头部
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        /*
         * 原始链表
         * 1 -> 2 -> 3 -> 4 -> 5
         */

        /*
         * 补齐头部防止只有一个元素时异常
         * 0 -> 1 -> 2 -> 3 -> 4 -> 5
         */
        ListNode node = new ListNode(0);
        node.next = head;

        ListNode current = head;
        int length = 0;
        while (current != null) {
            length++;
            current = current.next;
        }

        // 计算倒数的尾节点在哪里
        length -= n;
        current = node;
        while (length > 0) {
            length--;
            // 找到的节点其实为待删除节点的父节点
            current = current.next;
        }

        // current 为我们关心的节点
        current.next = current.next.next;

        return node.next;
    }
}
