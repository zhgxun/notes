package com.github.zhgxun.learn.notes.geektime.datastruct;

import java.util.Arrays;

/**
 * 堆和堆排序
 */
public class Heap {
    // 数组记录堆中的元素, 为了方便处理, 从数组元素下标1开始记录
    private int[] values;

    // 堆的容量
    private int capacity;

    // 堆中已经存储的元素个数
    private int count;

    // 初始化堆信息
    public Heap(int capacity) {
        this.values = new int[capacity + 1];
        this.capacity = capacity;
        this.count = 0;
    }

    // 直接初始化一个堆
    public Heap(int[] values) {
        this.count = this.capacity = values.length;
        this.values = new int[this.capacity + 1];
        System.arraycopy(values, 0, this.values, 1, this.count);
    }

    /**
     * 交换数组中的两个元素
     *
     * @param i 待交换的元素下标
     * @param j 待交换的元素下标
     */
    public void swap(int i, int j) {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    /**
     * 往堆中插入一个元素
     * <p>
     * 往堆中插入一个元素的方法有两个, 一般采用从下到上堆化的过程
     * 即将待插入的元素放在堆的最后一个节点位置, 即数组中的最后一个位置, 然后从它的根节点开始进行堆化
     *
     * @param value 待插入堆中的元素ø
     */
    public void insert(int value) {
        // 堆中已无空间可新增元素
        if (count >= capacity) {
            return;
        }

        // 数组往后移动一个位置
        count++;

        // 将待插入的元素放在堆的最后一个位置
        values[count] = value;

        // 从这个元素的跟节点开始进行堆化
        // 注意此时只需要比较当前节点的值和它的跟节点即可, 因为其它元素是满足堆结构的无需比较
        int i = count;
        // 将所有满足当前节点值大于它的根节点值的元素进行交换, 直到树的根节点为止
        while (i / 2 > 0 && values[i] > values[i / 2]) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    /**
     * 删除堆顶元素
     * <p>
     * 将堆的第一个元素移除即可
     * 带来的问题是堆不再是一颗完全二叉树, 因此需要重构堆使其保持为一颗完全二叉树
     * 重构的方法可以是从堆顶依次寻找剩下的最大值, 但这样会给原本无空洞的堆造成数组空洞
     * 因此有一个适当的处理是将堆顶移除后, 将堆的最后一个元素放在堆顶处并从堆顶开始堆化, 这样整个堆就不会带来数组空洞
     *
     * @return 移除的堆顶元素, 堆中没有元素返回 -1, 这个不恰当, 仅仅表示一个标识
     */
    public int removeTop() {
        // 堆为空
        if (count == 0) {
            return -1;
        }

        // 取出堆顶元素并将堆的最后一个元素放置到堆顶位置处
        int value = values[0];
        values[0] = values[count];

        // 进行堆化维持堆的特性
        count--;
        heapify(count, 1);
        return value;
    }

    /**
     * 对一个堆进行堆化维持堆的特性
     *
     * @param count 堆中待堆化的元素个数
     * @param top   从堆的某个位置开始堆化, 一般固定为堆顶位置1
     */
    public void heapify(int count, int top) {
        while (true) {
            // 当前堆顶元素需要跟左节点和右节点的值进行比较看是否需要交换
            int maxTPos = top;

            // 左节点 (2 * top) 的值比堆顶的值大则当前最大值为左节点值
            if (2 * top <= count && values[2 * top] > values[maxTPos]) {
                maxTPos = 2 * top;
            }
            // 右节点 (2 * top + 1) 的值比上一次最大值大则当前最大值为右节点
            if (2 * top + 1 <= count && values[2 * top + 1] > values[maxTPos]) {
                maxTPos = 2 * top + 1;
            }

            // 经过一轮比较, 当前顶节点和左右子节点的值比较后记录一个最大值, 可能为节点值本身, 也可能为左节点或者右节点值
            // 如果是本身则无需再继续进行堆化
            if (maxTPos == top) {
                break;
            }

            // 否则进行节点值交换
            swap(top, maxTPos);

            // 继续比较当前的根节点
            top = maxTPos;
        }
    }

    /**
     * 对一个数组建堆
     * <p>
     * 建堆需要对堆有一个记忆: 堆中 (n/2 + 1) ~ n 的节点均为叶子节点, 因此建堆只需要从堆顶 1 ~ n/2 进行建堆即可
     * <p>
     * 当然堆排序的首次建堆本无堆形式因此随便建一个堆即可, 可以认为数组的初始形式就一个树转化而来的
     */
    public void buildHeap() {
        for (int i = count / 2; i >= 1; --i) {
            heapify(count, i);
        }
    }

    /**
     * 对一个数组进行堆排序
     * <p>
     * 首先建堆, 建堆的时间复杂度是 O(n)
     * 其次是排序, 排序的时间复杂度是 O(n log n)
     * 所以堆排序的时间复杂度是 O(n log n), 堆排序是一种原地不稳定的排序算法, 比如原本有序的数组第一次建堆将被完全打乱
     */
    public void sort() {
        // 第一步是建堆, 建堆完毕后堆顶即为最大大元素
        buildHeap();

        // 第二步排序
        // 记录待处理的堆的元素个数
        int k = count;
        while (k > 1) {
            // 将堆顶的元素放置在数组的末尾处
            swap(1, k);
            // 每次对剩下的元素进行堆化
            k--;
            // 不要直接对k-- | k++ 进行传参
            heapify(k, 1);
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(new int[]{2, 3, 5, 6, 3, 7});
        heap.sort();
        System.out.println(Arrays.toString(heap.values));
    }
}
