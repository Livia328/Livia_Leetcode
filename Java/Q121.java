package Java;

import javafx.scene.input.PickResult;

public class Q121 {
    // brute force
    // time complexity: O(n^2)
    public static int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            int tmp = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > tmp) {
                    tmp = prices[j];
                }
            }
            ans = Math.max(ans, tmp - prices[i]);
        }
        return ans;
    }

    public static int maxProfit2(int[] prices) {
        //遍历一遍，记录目前经历过的最小值
        int min = prices[0];
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit2(prices));
    }
}
