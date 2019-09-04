package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

/**
 * 实现一个整形链表, 可以使用泛型, 道理类似, 这不是一个县城安全的链表
 * <p>
 * 以下索引均从0开始
 */
public class MyLinkedList {
    // 链表头
    private ListNode head = null;
    // 记录链表的长度
    private Integer size = 0;

    // 初始化
    public MyLinkedList() {
    }

    /**
     * 获取链表中第 index 个节点的值
     * 如果索引无效, 则返回-1
     *
     * @param index 节点
     * @return 节点的值
     */
    public int get(int index) {
        if (index < 0 || index > size - 1) {
            return -1;
        }
        ListNode cur = head;
        while (index > 0) {
            index--;
            cur = cur.next;
        }

        return cur.val;
    }

    /**
     * 在链表的第一个元素之前添加一个值为 val 的节点
     * 插入后, 新节点将成为链表的第一个节点
     *
     * @param val 待插入的值
     */
    public void addAtHead(int val) {
        // 如果链表为空, 则此时为第一个元素
        if (head == null) {
            head = new ListNode(val);
            head.next = null;
        } else {
            // 否则将链表连接到当前元素的后面, 重新指向头节点即可
            ListNode cur = new ListNode(val);
            cur.next = head;
            head = cur;
        }

        // 记录链表中的元素个数
        size++;
    }

    /**
     * 将值为 val 的节点追加到链表的最后一个元素
     *
     * @param val 待插入的值
     */
    public void addAtTail(int val) {
        // 链表为空
        if (head == null) {
            head = new ListNode(val);
            head.next = null;
        } else {
            // 需要遍历到链表的末尾, 注意这里不要直接走到null指针上, 不能在null指针上赋值, 否则链表丢失
            ListNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            // 此时cur为null, 即是链表的最后一个元素
            cur.next = new ListNode(val);
            cur.next.next = null;
        }

        size++;
    }

    /**
     * 在链表中的第 index 个节点之前添加值为 val  的节点
     * 如果 index 等于链表的长度, 则该节点将附加到链表的末尾
     * 如果 index 大于链表长度, 则不会插入节点
     * 如果index小于0, 则在头部插入节点
     *
     * @param index 期望插入的位置
     * @param val   待插入的值
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            // 不处理
        } else if (index == size) {
            // 尾部插入
            addAtTail(val);
        } else if (index > 0) {
            // 中间插入
            ListNode cur = head;
            // index > 1 说明找到待插入的上一个元素
            // index > 0 则找到的是当前元素
            while (index > 1) {
                cur = cur.next;
                index--;
            }
            System.out.println(cur.val);
            // 需要在cur的位置插入, 即是在cur的前面插入
            ListNode temp = new ListNode(val);
            // 将刚才找到的位置的下一个补在后面
            temp.next = cur.next;
            cur.next = temp;

            size++;
        } else {
            // 头部插入
            addAtHead(val);
        }
    }

    /**
     * 如果索引 index 有效, 则删除链表中的第 index 个节点
     *
     * @param index 期望删除的索引位置
     */
    public void deleteAtIndex(int index) {
        if (index > size || index < 0) {
            return;
        }

        // 仅有一个节点
        if (size == 1) {
            head = null;
            size--;
            return;
        }

        ListNode cur = head;
        // 删除当前节点的值
        while (index > 1) {
            cur = cur.next;
            index--;
        }
        cur.next = cur.next.next;

        size--;
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
//        linkedList.addAtTail(3);
//        linkedList.addAtIndex(1, 2);
        // 1->2->3
        PrintListNode.print(linkedList.head);
//        System.out.println(linkedList.get(1));
        linkedList.deleteAtIndex(0);
        PrintListNode.print(linkedList.head);
//        System.out.println(linkedList.get(1));
    }
}
