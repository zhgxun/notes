package github.banana.sort;

/**
 * 快速排序, 一种比较优秀的排序方案
 */
public class Quick {

    public static void main(String[] args) {
        int[] origin = RandList.getIntList(100000);
        long start = System.currentTimeMillis();
        SortUtil.quick(origin, 0, origin.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("消耗的时间: " + (end - start));
    }
}
