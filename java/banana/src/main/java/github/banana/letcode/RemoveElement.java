package github.banana.letcode;

/**
 * 从数组中移除目标值, 新数组追加都数组开始处
 */
public class RemoveElement {

    public static void main(String[] args) {
        // 1 2 6 3 4 5 6
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);
//        node.next.next = new ListNode(6);
//        node.next.next.next = new ListNode(3);
//        node.next.next.next.next = new ListNode(4);
//        node.next.next.next.next.next = new ListNode(5);
//        node.next.next.next.next.next.next = new ListNode(6);

        ListNode root = removeElements(node, 1);
//        System.out.println("1 2 3 4 5");
        System.out.println(" ");
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.next;
        }
        System.out.println(" ");
    }

    public static int removeElement(int[] nums, int val) {
        int length = nums.length;
        int j = 0;
        for (int i = 0; i < length; i++) {
            // 需要保留的值往数组前面追加
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }

        return j;
    }

    /**
     * 移除链表中的指定节点, 虽然为简单, 看提交次数还是提交比通过率大的多, 看来并不是简单的算法题就是轻而易举的呀
     * <p>
     * 处理建表和数据的经验就是, 添加一个头节点, 防止头被删除, 再加上双指针, 慢指针定位当前位置, 快指针往前探测符合条件的东西
     * <p>
     * 基本可以解决大部分的问题了
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head.val == val ? null : head;
        }

        ListNode node = new ListNode(0);
        node.next = head;

        ListNode slow = node, fast;
        while (slow != null && slow.next != null) {
            // 目标值所在的节点需要删除
            if (slow.next.val == val) {
                // 通过快速指针去寻找下一个不相同的元素为止
                fast = slow.next.next;
                while (fast != null && fast.val == val) {
                    fast = fast.next;
                }

                // 直接将链表接上
                slow.next = fast;
            }
            slow = slow.next;
        }

        return node.next;
    }

    /**
     * 该节点就是待删除的节点
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
