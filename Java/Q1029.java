import java.util.Arrays;

public class Q1029 {
    /*
     * 假设我们让所有人都去B市
     * 那么我们可以得到sumB
     * 
     * 但是我们要让N个人去A市
     * 改变这些人公司需要付出的代价是
     * cost[i][0]- cost[i][1] (+ priceA - priceB)
     * 
     * 因为我们可以按照每个人priceA - priceB
     * 选出最小的N个人去A市
     * （因为这样对总成本的降低是最大的）
     */
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> 
            (a[0] - a[1]) - (b[0] - b[1])
        );
        int sum = 0;
        int n = costs.length / 2;
        for (int i = 0; i < n; i++) {
            sum += costs[i][0] + costs[i + n][1];
        }
        return sum;
    }
}
