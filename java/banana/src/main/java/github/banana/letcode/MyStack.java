package github.banana.letcode;

import java.util.LinkedList;

// 用队列实现栈, 只要知道标准库里面的基本方法即可
// 不过这样似乎意义不大呀
public class MyStack {
    private LinkedList<Integer> queue = new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        if (queue.isEmpty()) {
            throw new RuntimeException("Queue is null");
        }
        return queue.pollLast();
    }

    public int top() {
        if (queue.isEmpty()) {
            throw new RuntimeException("Queue is null");
        }
        return queue.peekLast();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
