import java.util.Random;

public class Q528 {
    /*
     * 每一个数有对应的权重
     * 就像是线段，每段有不同的长度，落在这个长度上的概率是多少
     * 
     * 因为要计算和，所以prefix sum
     * 比如[1,3]
     * 
     * |___||_________|
     * 0   1          4
     * 
     * 在[0.4]之间随机生成数字，落在哪个区见中就应该是哪个数
     */
    class Solution {
        private int[] preSum;
        private Random rand = new Random();

        public Solution(int[] w) {
            int n = w.length;
            // preSum[i]为w[0]到w[i - 1]的和
            preSum = new int[n + 1];
            preSum[0] = 0;
            for (int i = 1; i <= n; i++) {
                preSum[i] = preSum[i - 1] + w[i - 1];
            }
        }
        
        public int pickIndex() {
            int n = preSum.length;
            // 从[1, preSum[n - 1]]中随机抽取一个数
            int target = rand.nextInt(preSum[n - 1]) + 1;
            int res = 0;
            for (int i = 0; i < preSum.length; i++) {
                if (preSum[i] >= target) {
                    res = i - 1;
                    break;
                }
            }
            return res;
        }
    }
}
