package github.banana.letcode;

public class MaxProfitV2 {

    // 注意这次必须是连续的上升段, 跟一次交易不同, 一次交易是寻找最大差值, 多次交易是累计每一次连续上升的利润之和
    public int maxProfit(int[] prices) {
        int left = 0;
        int length = prices.length - 1;

        // 初始化谷值和峰值
        int valley;
        int peak;
        // 最大利润和
        int maxProfit = 0;

        while (left < length) {
            // 下降的波段, 一直找到下降到谷底为止
            while (left < length && prices[left] >= prices[left + 1]) {
                left++;
            }

            // 此时记录谷底的值
            valley = prices[left];

            // 从谷底开始, 上升的波段, 连续找到上升的峰值
            while (left < length && prices[left] <= prices[left + 1]) {
                left++;
            }

            // 记录上升的的峰值
            peak = prices[left];

            // 累计该次获取的利润和
            maxProfit += peak - valley;
        }

        // 最终利润
        return maxProfit;
    }

    // 也是利用上升的谷和峰之间的利润累计, 不过这个可能不是很好想出来
    // 买入卖出只是一个抽象的概念, 累计利润是最终目的, 所以不是这么直接, 虽然很简洁的解决了这个问题
    public int maxProfitV2(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 上升点的累计值就是累计利润
            if (prices[i] > prices[i - 1]) {
                maxprofit += prices[i] - prices[i - 1];
            }
        }
        return maxprofit;
    }
}
