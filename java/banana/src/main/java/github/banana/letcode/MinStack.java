package github.banana.letcode;

import java.util.Stack;

// 借助栈来实现栈有点多余的意思, 其实底层应该是用数组来实现栈
// 同时维护一个最小栈
public class MinStack {
    private Stack<Integer> value;
    private Stack<Integer> minValue;

    public MinStack() {
        value = new Stack<>();
        minValue = new Stack<>();
    }

    // 将元素 x 推入栈中
    // 数组实现末尾为栈顶
    public void push(int x) {
        value.add(x);
        if (minValue.isEmpty() || minValue.peek() >= x) {
            minValue.add(x);
        } else {
            minValue.add(minValue.peek());
        }
    }

    // 删除栈顶的元素
    public void pop() {
        if (!value.isEmpty()) {
            value.pop();
            minValue.pop();
        }
    }

    // 获取栈顶元素
    public int top() {
        if (value.isEmpty()) {
            throw new RuntimeException("Stack is null");
        }
        return value.peek();
    }

    // 检索栈中的最小元素
    public int getMin() {
        if (minValue.isEmpty()) {
            throw new RuntimeException("Stack is null");
        }
        return minValue.peek();
    }
}
