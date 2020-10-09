public class BestTimeBuyandSellStock2T122 {
    public int maxProfit(int[] prices) {
//        int n = prices.length;
//        if (n == 0 || n == 1) return 0;
//        int lo = 0;
//        int sum = 0;
//        for (int i = 1; i < n; i++) {
//            if (prices[i] < prices[i - 1]) {
//                if (lo != i - 1) {
//                    sum += prices[i - 1] - prices[lo];
//                    lo = i;
//                }
//                else {
//                    lo++;
//                }
//            }
//            else {
//                if (i == n - 1) {
//                    sum += prices[i] - prices[lo];
//                }
//            }
//        }
//
//        return sum;

        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) max += prices[i] - prices[i - 1];
        }
        return max;
    }
}
