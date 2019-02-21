package github.banana.view;

/**
 * 算法和数据接口
 * <p>
 * 1. 数组
 * 一种线性表数据结构, 用一组连续的内存空间, 存储一组具有相同类型的数据
 * <p>
 * 线性表是数据排列成一条线一样的数据结构, 每个线性表上的数据最多只有前和后两个方向, 其实链表, 队列, 栈也都是线性表, 相对应的就是非线性表,
 * 比如树, 图, 堆等, 因为数据不是简单的前后关系
 * <p>
 * 数据因为具有连续的内存地址和相同的数据类型, 故只需要知道数组的首地址, 就可以随机根据数据下标访问数据, 但是插入和删除, 则需要大量数据搬移
 * <p>
 * 因此数组的插入和删除操作衍生出了一些高效的方案
 * 比如 插入数据时, 仅仅是将待插入的位置的元素移动到数组的末尾, 避免了数据搬移
 * 而删除也是类似的道理, 不直接删除数据, 而是将其进行标记为删除状态, 待需要时在搬移数据进行删除, 类似垃圾回收中的标记清除, 在特定的时候统一进行
 * 数据搬移, 而不是每次都搬移数据
 * <p>
 * 数组还有个特点是大小是固定的, 搬移数据需要申请一份大的内存空间, 将旧数据拷贝到新的内存地址, 资源消耗相当大
 * <p>
 * 2. 缓存
 * 缓存的大小有限, 当缓存被用满时, 需要使用缓存淘汰策略, 常见的策略有三种:
 * FIFO 先进先出策略
 * LFU 最少使用策略
 * LRU 最近最少使用策略
 * <p>
 * 3. 链表
 * 链表通过指针将一组零散的内存块串联起来使用
 * 最常见的三种: 单链表, 双链表和循环链表
 * <p>
 * 4. 栈
 * 特定的数据结构对应特定的场景, 数据或链表暴露了太多的操作接口
 * 栈主要包括2个操作, 入栈和出栈, 栈顶插入一个数据栈顶删除一个数据
 * 栈既可以用数组实现, 也可以用链表实现
 * 用数组实现的栈叫顺序栈, 用链表实现的栈叫链式栈
 * 我们说空间复杂度的时候, 是指除了原本的数据存储空间, 算法运算还需要额外的存储空间, 这部分才算空间复杂度
 * {@link ArrayStack} 参考这个简单的实现
 * <p>
 * 5. 队列
 * 入队, 放一个数据到队列尾部
 * 出队, 从队列头部取一个元素
 * 队列跟栈一样, 也是一直操作受限的线性表数据结构
 * 作为一种基础的数据结构, 队列在应用也非常方便, 特别是一些具有某些额外特性的队列, 比如循环队列, 阻塞队列, 并发队列
 * 它们在很多偏底层系统, 框架, 中间件的开发中起着关键性的作用, 比如 Java 中的 {@link java.util.concurrent.ArrayBlockingQueue}, 甚至
 * 实现了公平锁和非公平锁的实现
 * <p>
 * 用数组实现的队列是顺序队列, 用链表实现的队列是链式队列
 * {@link ArrayQueue} 队列简单实现
 * 对于栈来说, 只需要一个栈顶指针即可, 但是对于队列来说, 需要两个指针来标识队列的头和尾
 * <p>
 * 队列也有个问题, 即是不断的入队到队列满时, 尾指针达到最大值, 头指针也达到最大值, 即元素都出队结束, 队列其实为空, 但是却不可用
 * 数据搬移, 类似扩索容操作, 重新初始化队列的头尾指针
 */
public class DataStructTest {

    public static void main(String[] args) {
        // 1. 栈
        System.out.println("栈");
        ArrayStack stack = new ArrayStack(5);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        // return null
        stack.push("e");
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }

        // 2. 顺序队列
        System.out.println("顺序队列");
        ArrayQueue queue = new ArrayQueue(5);
        queue.enQueue("aa");
        queue.enQueue("bb");
        queue.enQueue("cc");
        queue.enQueue("dd");
        queue.enQueue("ee");
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.deQueue());
        }
        // 此时入队成功
        queue.enQueue("ff");
        System.out.println(queue.deQueue());

        // 3. 链式队列
        System.out.println("链式队列");
        LinkQueue linkQueue = new LinkQueue();
        linkQueue.en("abc");
        linkQueue.en("abd");
        linkQueue.en("abe");
        linkQueue.en("abf");
        linkQueue.en("abg");
        // 遍历链式队列, 需要知道头指针
        LinkQueue.Node head = linkQueue.getHead();
        while (head != null) {
            // 直接出队即可
            System.out.println(linkQueue.de());
            head = head.next;
        }
    }
}

/**
 * 实现一个简单的栈结构
 * 不支持动态扩容
 */
class ArrayStack {
    // 存储原始数据
    private String[] items;
    // 栈的大小
    private int length;
    // 栈中元素的个数
    private int count;

    /**
     * 初始化一个栈, 栈的大小为容量大小, 栈中的元素为0个
     *
     * @param length 栈的容量
     */
    ArrayStack(int length) {
        items = new String[length];
        this.length = length;
        count = 0;
    }

    /**
     * 入栈一个元素
     *
     * @param item 待入栈的元素
     * @return 入栈结果
     */
    public boolean push(String item) {
        // 栈空间已满, 直接拒绝
        if (count == length) {
            return false;
        }
        // 将元素存储在当前的位置
        items[count] = item;
        ++count;
        return false;
    }

    // 出栈
    public String pop() {
        // 栈中没有元素
        if (count <= 0) {
            return null;
        }
        // 获取最新入栈的栈对象
        String s = items[count - 1];
        --count;
        return s;
    }
}

/**
 * 简单队列
 * 无法利用空间, 满之后不可再使用
 * 补充数据搬移操作
 */
class ArrayQueue {
    // 队列底层数据结构
    private String[] items;
    // 队列的长度
    private int capacity;
    // 队列头
    private int head;
    // 队列尾
    private int tail;

    /**
     * 一个概念就是
     * 头尾是相对的, 随着数据的入队和出队而变化
     * 入队的时候尾指针往后移动, 即增加
     * 出队的时候, 头指针往后移动, 也是增加
     */

    ArrayQueue(int capacity) {
        items = new String[capacity];
        this.capacity = capacity;
        this.head = 0;
        this.tail = 0;
    }

    /**
     * 入队
     * 需要考虑数据搬移操作
     *
     * @param item 待入队的对象
     * @return 入队结果
     */
    public boolean enQueue(String item) {
        // 队列头尾指针相撞
        if (tail == capacity) {
            // 队列是否还有空间, 看元素数量是否是最大容量
            // 即看出队的头指针移动位置, 0则为未出队, 队列暂无更多空间
            // 搬移也无法操作, 只能扩容到更大的数组
            if (head == 0) {
                return false;
            }
            // 否则进行数据搬移
            // 看头指针的位置, 进行数据搬移
            // 其实就是将尾部元素移动到头部去
            /*
             *                               head             tail
             * |--------------------------------------------------
             * |  |  |  |  |  |  |  |  |  |  | 3 | 5 | 8 | 2 | 4 |
             * |--------------------------------------------------
             */
            // 类似上述队列, 方向无所谓, 统一按队列一边入队, 一边出队即可
            // 此时 tail 就到达容量处, 但是 head 之前有大量的空缺, 故此时触发数据搬移工作
            // 即将数据全部搬移到 head = 0 开始, 并更新 tail 指针
            if (head < tail) {
                for (int i = head; i < tail; ++i) {
                    items[i - head] = items[i];
                }
                // 更新头尾指针
                head = 0;
                tail -= head;
            } else {
                /*                   head
                 * |---------------------
                 * |  |  |  |  |  |  |  |
                 * |---------------------
                 *                   tail
                 * 这种情况比较特殊, 无数据可搬移, 需要重新移动指针到开始位置即可
                 */
                // 如果队列满后再入队, 更新指针为初始值即可
                head = tail = 0;
            }
        }
        // 往队尾添加元素, 不影响, 只关注尾指针即可
        items[tail] = item;
        ++tail;
        return true;
    }

    /**
     * 其实出队不需要关心数据是否搬移, 正常出队即可
     *
     * @return
     */
    public String deQueue() {
        // 队列为空
        if (tail == head) {
            return null;
        }
        // 头部元素出队
        String s = items[head];
        // 头部元素后移
        ++head;
        return s;
    }
}

/**
 * 链式队列
 * 可以理解为链式为无限队列, 受系统资源限制
 */
class LinkQueue {

    // 保存队列的头和尾指针
    private Node head;
    private Node tail;

    LinkQueue() {
        head = null;
        tail = null;
    }

    /**
     * 链表
     */
    public static class Node {
        // 存储当前对象
        String value;
        // 下一个节点
        Node next;

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void en(String item) {
        // 首次入队, 头尾指针都指向同一个节点
        if (tail == null) {
            /**
             * head
             * |--------------
             * | item | next | -> null
             * |--------------
             * tail
             */
            // 节点的下一个节点默认为null, 暂时还不存在节点
            Node node = new Node(item, null);
            head = node;
            tail = node;
        } else {
            // 需要实时更新队尾数据
            /**
             * head 位置
             * |--------------    |--------------
             * | item | next | -> | item | next | -> null
             * |-------------|    |--------------
             *                    tail 位置
             */
            // 队尾, 入队都把数据添加到队尾, 先进后出顺序
            tail.next = new Node(item, null);
            // 更新当前队尾的位置其实就是最后一个入队的数据
            tail = tail.next;
        }
    }

    public String de() {
        // 没有元素要出队
        if (head == null) {
            return null;
        }
        String value = head.value;
        head = head.next;
        // 最后一个位置, 队列中已无元素
        if (head == null) {
            tail = null;
        }
        return value;
    }

    /**
     * 暴露头指针, 提供遍历链式队列
     *
     * @return 头指针
     */
    public Node getHead() {
        return head;
    }
}
