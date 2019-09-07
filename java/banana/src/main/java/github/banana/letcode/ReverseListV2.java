package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

public class ReverseListV2 {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        PrintListNode.print(node);
        PrintListNode.print(reverseListV2(node));
    }

    // 新节点添加法
    // 效率不是很优秀但已经不差
    // 但是更容易理解一些
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        // 定义一个新节点, 链表通用的处理方式
        ListNode node = new ListNode(0);

        ListNode cur = head;
        while (cur != null) {
            // 记录下一个节点, 避免被修改指向
            ListNode next = cur.next;

            // 此时当前节点是 cur 应该被处理为头结点, 拼接已有节点至它的下一个节点
            // node是虚拟节点, 真实节点从node.next开始, 即是 node.next 始终是新节点的头结点
            cur.next = node.next;

            // 重置链表的下一个节点
            node.next = cur;

            // 当前节点继续前进
            cur = next;
        }

        return node.next;
    }

    // 效率比较优秀的是原地交换
    // 记录上一次被交换的元素
    // 链表交换需要注意边界
    // 这两种方式其实是一样的道理
    public static ListNode reverseListV2(ListNode head) {
        // 上一个元素, 初始注意是空
        ListNode pre = null;
        // 当前元素
        ListNode cur = head;
        // 下一个元素
        ListNode next;
        while (cur != null) {
            // 记录下一个元素, 避免交换丢失
            next = cur.next;

            // 进行变换
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 注意返回 cur 最后是null, pre 才是最终的头节点
        return pre;
    }
}
