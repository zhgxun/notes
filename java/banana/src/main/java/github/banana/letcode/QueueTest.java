package github.banana.letcode;

public class QueueTest {

    public static void main(String[] args) {
        MyCircularQueue queue = new MyCircularQueue(3);
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        System.out.println("true: " + queue.enQueue(1));
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        System.out.println("true: " + queue.enQueue(2));
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        System.out.println("true: " + queue.enQueue(3));
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        System.out.println("false: " + queue.enQueue(4));
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        System.out.println("1,2,3 -> " + queue);
        System.out.println("3 -> " + queue.Rear());
        System.out.println("true: " + queue.isFull());
        System.out.println("true: " + queue.deQueue());
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        System.out.println("true: " + queue.enQueue(4));
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        System.out.println("1,2,3,4 -> " + queue);
        System.out.println("4 -> " + queue.Rear());
    }
}
