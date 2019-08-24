package github.banana.letcode;

public class SearchMatrix {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 3, 7}, {10, 11, 20}, {23, 30, 50}};
        System.out.println(searchMatrix(nums, 7));
        System.out.println(searchMatrix(nums, 8));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        // 矩阵的深度
        int height = matrix.length;
        if (height == 0) {
            return false;
        }
        // 矩阵的宽度
        int width = matrix[0].length;

        int left = 0, right = height * width - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            // 中间元素的值, 需要转化下标
            // 就是个标准的二分查找,  但是下标的转化相当奇特, 很难想到
            // 二维与一维坐标映射
            // 二维数组行 = 一维坐标 / matrixColSize
            // 二维数组列 = 一维坐标 % matrixColSize
            // 理解了坐标映射就相当的简单, 就是一维的二分查找了
            int value = matrix[middle / width][middle % width];
            if (value == target) {
                return true;
            } else if (value > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return false;
    }
}
