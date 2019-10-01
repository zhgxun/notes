package github.banana.letcode;

import java.util.Arrays;

/**
 * 实现一个简单的循环队列
 */
public class MyCircularQueue {
    private int[] queue;
    public int front;
    public int rear;
    private int maxSize;

    /**
     * 构造器, 设置队列长度为 k
     */
    public MyCircularQueue(int k) {
        maxSize = k + 1;
        queue = new int[maxSize];
    }

    /**
     * 向循环队列插入一个元素
     * <p>
     * 如果成功插入则返回真
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        queue[rear] = value;
        // rear 始终指向末尾的后一个元素, 所以添加时直接将其放入即可
        rear = (rear + 1) % maxSize;
        return true;
    }

    /**
     * 从循环队列中删除一个元素
     * <p>
     * 如果成功删除则返回真
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        // front 本身指向对首元素, 故前进即可
        front = (front + 1) % maxSize;
        return true;
    }

    /**
     * 从队首获取元素
     * <p>
     * 如果队列为空返回 -1
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }

        return queue[front];
    }

    /**
     * 获取队尾元素
     * <p>
     * 如果队列为空返回 -1
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }

        if (rear == 0) {
            return queue[maxSize - 1];
        }
        return queue[(rear - 1) % maxSize];
    }

    /**
     * 检查循环队列是否为空
     */
    public boolean isEmpty() {
        // 相等则为空
        return front == rear;
    }

    /**
     * 检查循环队列是否已满
     */
    public boolean isFull() {
        // 环形后相等则为满
        return (rear + 1) % maxSize == front;
    }

    @Override
    public String toString() {
        return "Queue[" + Arrays.toString(queue) + "]";
    }
}
