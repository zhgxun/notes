package com.github.zhgxun.learn.notes.leetcode;

import java.util.Stack;

/**
 * 面试题09. 用两个栈实现队列
 * <p>
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 */
public class CQueue {
    // 1 <= values <= 10000

    /*
     * 队列先进先出
     * 考虑用两个栈, 压栈为先进入元素, 出栈为推出元素
     *
     * | 4 |  | 2 |
     * | 3 |  | 3 |
     * | 1 |  | 1 |
     * | 3 |  | 3 |
     * | 2 |  | 4 |
     * -----  -----
     *  压栈   出栈
     */

    // 压栈
    Stack<Integer> inStack = new Stack<>();
    Stack<Integer> outStack = new Stack<>();

    public CQueue() {

    }

    // 在队列尾部插入整数
    public void appendTail(int value) {
        inStack.push(value);
    }

    // 在队列头部删除整数
    // 若队列中没有元素，deleteHead 操作返回 -1
    public int deleteHead() {
        // 如果出栈有数据直接出栈
        if (!outStack.empty()) {
            return outStack.pop();
        }

        // 出入站均为空则无数据可处理
        if (inStack.empty()) {
            return -1;
        }

        // 将入栈中的数据拷贝到出栈中
        // 反转元素顺序
        while (!inStack.empty()) {
            outStack.push(inStack.pop());
        }

        return outStack.pop();
    }

    public static void main(String[] args) {
        // 压栈
        CQueue queue = new CQueue();
        queue.appendTail(1);
        queue.appendTail(2);
        queue.appendTail(3);
        queue.appendTail(4);
        queue.appendTail(5);

        // 出栈
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }
}
