package github.banana.letcode;

import github.banana.common.ListNode;

public class InsertionSortList {

    public static void main(String[] args) {
        ListNode node = new ListNode(4);
        node.next = new ListNode(2);
        node.next.next = new ListNode(1);
        node.next.next.next = new ListNode(3);
        ListNode root = new InsertionSortList().insertionSortList(node);
        while (root != null) {
            System.out.println(root.val);
            root = root.next;
        }
    }

    // 插入排序, 时间复杂度O(n^2)效率中等
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 链表操作的哑节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // head~pre是排好序的部分
        // pre 记录当前节点的上一个节点
        ListNode pre = head;

        // 第一个元素默认是有序的
        // 从第二个元素开始比较
        ListNode cur = head.next;
        while (cur != null) {
            // 寻找插入位置
            // 每次都从头开始遍历, 寻找第一次大于当前节点的位置
            // 说明当前元素应该插入该点的前面
            // 即是 cur 要追加到 insertPre 的后面
            ListNode insertPre = findInsertIndexPre(dummyHead, cur);

            // 这种情况表示当前节点不需要换位置
            // 两个节点重合, 是同一个数或者已排序链表的末尾
            // 将最新的有序末尾指向当前即可
            // 该部分保持有序
            // 继续前进
            if (insertPre == pre) {
                pre = cur;
                cur = cur.next;
            } else {
                // cur需要插入到insertPre后面的位置
                /*
                 *                      4(cur)
                 *                      |
                 *                      v
                 * -> 1 -> 3(insertPre) -> 5(pre) ->
                 *
                 * 就是说 4 要插入到 3 和 5 之间
                 * 首先将 5 即是上一个有序链表末尾 pre 的下一个指向当前的 cur 的下一个
                 * 其次 cur 的下一个其实就是 pre, 然而 cur.next 无法直接指向 pre, 需要借助 pre 的上一个 insertPre.next 来指向
                 * 最后 找到 cur 的上一个节点, 其实就是 insertPre, 直接指向即可 insertPre.next = cur
                 * 这样就完成了3个链表节点的交换
                 * 还是比较烧脑的, 链表就这点特别复杂, 弄错就互相找不到了
                 */
                // 当前节点要被插入到其它地方, 可能相邻也可能不相邻, 故需要记录当前节点, 避免丢失
                // 完全可以用一个临时节点来记录, 不一定非要复用pre, 减少一次空间复杂度而已
                pre.next = cur.next;
                // 这个交换就比较简单了, 其实就是找到待插入位置的上一个节点, 设置他们的下一个节点位置即可
                cur.next = insertPre.next;
                insertPre.next = cur;

                // 移动cur, 之前不能丢失当前节点, 否则链表找不到位置了, 哈哈哈
                cur = pre.next;
            }
        }

        return dummyHead.next;
    }

    /**
     * 查找cur要插入位置的前一个节点
     *
     * @param head 链表的头节点
     * @param cur  当前节点
     * @return 当前节点应该在链表中的位置, 说明cur应该插入在该节点的后面
     */
    private ListNode findInsertIndexPre(ListNode head, ListNode cur) {
        while (head.next != cur) {
            if (head.next.val >= cur.val) {
                return head;
            }
            head = head.next;
        }
        return head;
    }
}
