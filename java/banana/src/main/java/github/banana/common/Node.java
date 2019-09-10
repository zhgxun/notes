package github.banana.common;

/**
 * 带随机指针的链表
 */
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {
    }

    public Node(int val, Node next, Node random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }
}
