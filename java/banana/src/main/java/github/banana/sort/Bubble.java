package github.banana.sort;

/**
 * 冒泡排序
 * <p>
 * 每次比较后, 已比较过的元素中为有序序列, 未比较的元素序列越来越小
 * 复杂度上 O(n^2) = O(n) * O(n)
 * 总体上是最慢的排序算法, 也是最简单的学习案例
 */
public class Bubble {

    public static void main(String[] args) {
        int[] origin = RandList.getIntList(100000);
        long start = System.currentTimeMillis();
        SortUtil.bubble(origin);
        long end = System.currentTimeMillis();
        System.out.println("消耗的时间: " + (end - start));
    }
}
