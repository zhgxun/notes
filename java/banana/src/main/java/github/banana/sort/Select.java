package github.banana.sort;

/**
 * 选择排序, 时间复杂度为 O(n^2) = O(n) * O(n), 跟插入排序基本相似, 实际上选择排序比插入排序稍慢一些, 但都比冒泡排序要快
 * 因为选择排序总是要遍历剩余序列的所有元素, 而插入排序可以不用完全遍历已排序的元素即可完成排序
 */
public class Select {

    public static void main(String[] args) {
        int[] origin = RandList.getIntList(100000);
        long start = System.currentTimeMillis();
        SortUtil.select(origin);
        long end = System.currentTimeMillis();
        System.out.println("消耗的时间: " + (end - start));
    }
}
