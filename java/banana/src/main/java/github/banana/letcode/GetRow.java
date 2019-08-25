package github.banana.letcode;

import java.util.ArrayList;
import java.util.List;

public class GetRow {

    public static void main(String[] args) {
        System.out.println(getRow(1));
        System.out.println(getRow(2));
        System.out.println(getRow(3));
        System.out.println(getRow(4));
        System.out.println(getRow(5));
    }

    // 跟打印杨辉三角一致, 区别在于按索引输出, 需要注意的是边界为0的条件
    // 该种方法需要占用两份list空间
    // 可以优化为只占用1个list空间, 即每次复写本身
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        // 第一个固定的值
        pre.add(1);

        // 排除特殊值
        if (rowIndex == 0) {
            return pre;
        }

        for (int i = 1; i < rowIndex + 1; i++) {
            // 当前行
            List<Integer> row = new ArrayList<>();
            // 上一行, 首次为初始值

            // 第一个数
            row.add(1);

            for (int j = 0; j < i - 1; j++) {
                row.add(pre.get(j) + pre.get(j + 1));
            }

            // 最后一个数
            row.add(1);

            // 将该行变更为上一行, 遍历完毕同时也是最后一行
            pre = row;
        }

        return pre;
    }
}
