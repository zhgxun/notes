package com.github.zhgxun.learn.notes.leetcode;

import com.github.zhgxun.learn.notes.ListNode;

import java.util.Arrays;

/**
 * 面试题06. 从尾到头打印链表
 * <p>
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 */
public class ReversePrint {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(3);
        node.next.next = new ListNode(2);

        System.out.println(Arrays.toString(reversePrint(node)));
    }

    // 因为是整数, 所以投机取巧从数组后面写入元素即可
    // 难点是不知道元素个数, 需要一次遍历
    // 最优解是一次遍历完知道元素个数也完成打印
    // 否则还需要一次遍历
    public static int[] reversePrint(ListNode head) {
        // 一次遍历知道元素个数
        int num = 0;
        ListNode cur = head;
        while (cur != null) {
            num++;
            cur = cur.next;
        }

        // 二次遍历直接输出目标元素
        int[] target = new int[num];
        cur = head;
        while (cur != null) {
            target[--num] = cur.val;
            cur = cur.next;
        }

        return target;
    }
}
