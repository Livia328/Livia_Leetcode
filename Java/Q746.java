public class Q746 {
    /*
     * DP;
     * target: finding the min cost to reach the top steps
     * 
     * it is minCost[i - 1] + cost[i], or minCost[i - 2] + cost[i -1]
     * so minCost[i] = Math.min(minCost[i - 1] + cost[i], minCost[i - 2] + cost[i -1])
     * 
     * 因为递推方程里有i-2，为了不越界，遍历的时候就要从i = 2开始
     * 所有minCost[0], minCost[1]就要手动初始化
     * 
     * minCost[0] = 0;
     * minCost[1] = Math.min(cost[1], cost[0])
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] minCost = new int[cost.length];
        minCost[0] = 0;
        minCost[1] = Math.min(cost[0], cost[1]);
        for (int i = 2; i < cost.length; i++) {
            minCost[i] = Math.min(minCost[i - 1] + cost[i], minCost[i - 2] + cost[i - 1]);
        }
        return minCost[cost.length - 1];
    }
}
