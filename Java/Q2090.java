import java.util.Arrays;

public class Q2090 {
    /*
     * sliding window
     * 
     * 初始化res[i] = -1
     * 在到达窗口大小前只往里加
     * 
     * nums[0: 2k + 1]作为初始窗口值
     * 后续更新先减去nums[i - k]，再加上nums[i + k]
     * 
     * [1 3 2] 4 5     k = 1
     *         i
     */
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        if (n < 2 * k + 1) {
            return res;
        }
        long sum = 0;
        // 扩张窗口到初始窗口
        for (int i = 0; i <= 2 * k; i++) {
            sum += nums[i];
        }
        res[k] = (int)(sum / (2 * k + 1));
        // 每次挪出再加入
        for (int i = k + 1; i < n; i++) {
            if (i + k >= n) {
                return res;
            }
            sum -= nums[i - k - 1];
            sum += nums[i + k];
            res[i] = (int)(sum / (2 * k + 1));
        }
        return res;
    }
}
