package github.banana.letcode;

import github.banana.common.ListNode;

public class IsPalindromeV2 {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        System.out.println(isPalindromeV2(node));
    }

    // 用字符串的思路来解决是比较容易想到的方法, 但是该方法是比较笨的办法效率并不高
    // 但确实最有简单和容易想到的办法
    // 只需要记住不要把数字分开即可
    // 26个测试用例耗时31ms已经达到最长耗时
    public static boolean isPalindrome(ListNode head) {
        // 空和仅有一个节点也是回文链表
        if (head == null || head.next == null) {
            return true;
        }

        StringBuilder sb = new StringBuilder();
        ListNode node = head;
        while (node != null) {
            sb.append(node.val);
            sb.append("#");
            node = node.next;
        }
        String number = sb.toString();

        String[] result = number.split("\\#");
        int length = result.length;
        StringBuilder res = new StringBuilder(length);
        for (int i = length - 1; i >= 0; i--) {
            res.append(result[i]);
            res.append("#");
        }

        return number.equals(res.toString());
    }

    // 快慢指针法
    // 找到链表的中点
    // 反转其中一部分
    // 验证是否完全一致即可
    // 这个效率就上去了, 但是理解就稍微复杂一些, 注意找中点和奇偶节点数的确定
    // 如果快指针刚好走到末尾说明是奇数个节点, 此时慢指针需要主动前进一步
    public static boolean isPalindromeV2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 快慢指针找到链表的中点
        // 慢指针一次前进一步快指针一次前进两步
        // 快指针到达链表末尾则慢指针刚好走到中点位置
        ListNode fast = head.next.next;
        // 从第一个元素开始前进一步
        ListNode slow = head.next;
        // fast.next 需要处理否则跳2步会空指针
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 翻转链表前半部分
        ListNode pre = null;
        ListNode next;
        while (head != slow) {
            // 反转需要记得提前记录下一个节点最后返回否则当前节点就丢失了
            next = head.next;

            // 当前节点后面拼接上次已经翻转的部分
            head.next = pre;
            // 记录该次翻转为下一次的上一次翻转部分
            pre = head;

            // 继续前进刚才记录的当前节点
            head = next;
        }

        // 如果是奇数个节点, 去掉后半部分的第一个节点
        // 奇偶链表的检查
        // 奇数时放好跳转到最后一个元素非空, 此时相当于慢指针
        if (fast != null) {
            slow = slow.next;
        }

        // 回文校验, 记得是翻转后的链表和慢指针记录的链表
        while (pre != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }

        return true;
    }
}
