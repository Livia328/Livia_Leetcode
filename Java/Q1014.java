import java.util.Arrays;

public class Q1014 {
    /*
     * values[i] + values[j] + i - j
     * =>
     * (values[i] + i) + (values[j] - j)
     * 所以只需要遍历一遍, 不断更新当前所得到的values[i] + i值就行
     */
    public int maxScoreSightseeingPair(int[] values) {
        int max_plus = 0;
        int res = 0;
        for (int j = 0; j < values.length; j++) {
            // 因为对于当前j，i只能是她之前的数
            res = Math.max(res, max_plus + values[j] - j);
            // 更新max plus
            max_plus = Math.max(max_plus, values[j] + j);
        }
        return res;
    }
}