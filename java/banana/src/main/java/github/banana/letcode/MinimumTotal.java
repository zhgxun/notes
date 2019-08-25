package github.banana.letcode;

import java.util.ArrayList;
import java.util.List;

public class MinimumTotal {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(2);
        triangle.add(row1);

        List<Integer> row2 = new ArrayList<>();
        row2.add(3);
        row2.add(4);
        triangle.add(row2);

        List<Integer> row3 = new ArrayList<>();
        row3.add(6);
        row3.add(5);
        row3.add(7);
        triangle.add(row3);

        List<Integer> row4 = new ArrayList<>();
        row4.add(4);
        row4.add(1);
        row4.add(8);
        row4.add(3);
        triangle.add(row4);

        minimumTotal(triangle);
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        // 三角形的层数
        int row = triangle.size();

        // 记录目标数组
        int[] dp = new int[row];
        // 初始化为最大的一个数组, 即是最后一个数组
        for (int i = 0; i < row; i++) {
            dp[i] = triangle.get(row - 1).get(i);
        }

        // 三角形的层数
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }

        return dp[0];
    }
}
