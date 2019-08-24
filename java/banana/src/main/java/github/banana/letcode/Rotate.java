package github.banana.letcode;

import java.util.Arrays;

public class Rotate {

    public static void main(String[] args) {
//        int[][] nums = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] nums = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate(nums);

    }

    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        // 注意奇偶个数
        for (int i = 0; i < (length + 1) / 2; i++) {
            // 转一半, 因为是旋转, 让j来旋转
            for (int j = 0; j < length / 2; j++) {
                // 赋值方式为顺时针一次赋值, 认准左下角开始
                // 横轴方向上的数字变化, 纵轴上的标识不变化
                // 纵轴方向上的数字变化, 横轴上的标识不变化
                // 记录当前左下角
                int temp = matrix[length - 1 - j][i];
                // 当前左下角用右下角来覆盖
                matrix[length - 1 - j][i] = matrix[length - 1 - i][length - 1 - j];
                // 右下角用右上角来覆盖
                matrix[length - 1 - i][length - 1 - j] = matrix[j][length - 1 - i];
                // 右上角用左上角来覆盖
                matrix[j][length - 1 - i] = matrix[i][j];
                // 左上角用左下角来覆盖
                matrix[i][j] = temp;
            }
        }
    }

    private static void print(int[][] nums) {
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }
}
