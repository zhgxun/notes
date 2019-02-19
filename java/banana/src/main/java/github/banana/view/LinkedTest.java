package github.banana.view;

/**
 * 写一个简单的双向链表
 */
public class LinkedTest {

    public static void main(String[] args) {
        // 头元素
        Node<Integer> first = new Node<>(null, 1, null);
        // 第一个元素
        Node<Integer> second = new Node<>(first, 2, null);
        // 并且将第二个元素与第一个元素关联
        first.next = second;

        // 第三个元素
        Node<Integer> third = new Node<>(second, 3, null);
        second.next = third;

        // 第四个元素
        Node<Integer> fourth = new Node<>(third, 4, null);
        third.next = fourth;

        // 遍历链表
        System.out.println("期望输出 1,2,3,4");
        Node<Integer> current = first;
        while (current != null) {
            System.out.println("链表数据: " + current.item);
            current = current.next;
        }
    }
}

/**
 * 链表结构
 *
 * @param <T>
 */
class Node<T> {
    T item;
    Node<T> prev;
    Node<T> next;

    Node(Node<T> prev, T item, Node<T> next) {
        this.prev = prev;
        this.item = item;
        this.next = next;
    }
}
