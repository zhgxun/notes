package github.banana.letcode;

public class MaxProfit {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    // 7,1,5,3,6,4
    // 数组中分布的数据有个规律, 就是连续数据中的最小值和最大值之差, 即是要寻找的数据
    public static int maxProfit(int[] prices) {
        int length = prices.length;
        int minPrice = Integer.MAX_VALUE;
        int maxAmount = 0;

        // 抽象为连续数据段中的最大值和最小值
        for (int i = 0; i < length; i++) {
            // 存在最小值, 只需要更新最小值
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxAmount) {
                // 不是最小值时需要计算当前利润
                maxAmount = prices[i] - minPrice;
            }
        }

        return maxAmount;
    }
}
