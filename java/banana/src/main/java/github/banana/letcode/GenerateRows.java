package github.banana.letcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateRows {

    public static void main(String[] args) {
        System.out.println(generate(5));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }

        // 第一个行固定为1
        res.add(new ArrayList<>());
        res.get(0).add(1);

        // 否则存在多行, 这里从第2行开始, 因为上一行固定为1
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            // 第一个元素固定为1
            row.add(1);

            // 中间元素使用上一个行来填充
            List<Integer> pre = res.get(i - 1);
            // 注意这里是下标, 最后一个无需遍历, 因为操作已经添加
            for (int j = 0; j < i - 1; j++) {
                row.add(pre.get(j) + pre.get(j + 1));
            }

            // 最后一个元素也固定为1
            row.add(1);

            // 将该行存入结果集中
            res.add(row);
        }

        return res;
    }
}
