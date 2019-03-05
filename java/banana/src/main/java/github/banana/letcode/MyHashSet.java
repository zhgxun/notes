package github.banana.letcode;

/**
 * 705. 设计哈希集合
 * <p>
 * 不使用任何内建的哈希表库设计一个哈希集合
 * <p>
 * 更简单的办法是使用boolean类型, 因为不需要获取数
 * 当然真正的哈希可不是这么简单的不过就是这个的完善版
 */
public class MyHashSet {
    private int[] items = new int[10000];

    public MyHashSet() {

    }

    public void add(int key) {
        items[key] = 1;
    }

    public void remove(int key) {
        items[key] = 0;
    }

    public boolean contains(int key) {
        return items[key] == 1;
    }
}
