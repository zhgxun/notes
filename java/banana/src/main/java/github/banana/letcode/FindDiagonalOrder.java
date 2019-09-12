package github.banana.letcode;

import java.util.Arrays;

public class FindDiagonalOrder {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        System.out.println(Arrays.toString(findDiagonalOrder(nums)));
    }

    public static int[] findDiagonalOrder(int[][] matrix) {
        int rowLength = matrix[0].length;
        int colLength = matrix.length;
        int UP = 0;
        int DOWN = 1;

        // 对角线的数量为矩阵维度和少1
        int line = rowLength + colLength - 1;
        int STATE = UP;
        for (int i = 0; i < line; i++) {
            switch (STATE) {
                case 0:
                    STATE = DOWN;
                    i++;
                    break;
                case 1:
                    STATE = UP;
                    i++;
                    break;
            }
        }

        return new int[]{0};
    }
}
