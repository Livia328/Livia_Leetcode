import java.util.HashMap;

public class Q1124 {
    /*
     * 我觉得我们可以统一处理一下
     * 大于8的情况和小于8的情况
     * 
     * 大于8标记为1
     * 小于8标记为-1
     * 
     * 这样问题就成为了
     * 计算数组中元素和 > 0的子数组的最大长度
     * 
     * preSum
     * 
     * 分情况讨论
     * 如果preSum[i] > 0
     * 那么说明[0, i]都是表现良好的时段
     * update res with current index
     * 
     * 如果preSum[i] < 0
     * 那么需要找到j, preSum[i] - preSum[j]  > 0
     * 
     * 因为preSum是从0开始，然后每一个nums[i]要不是1，要不是-1
     * 所以寻找preSum[j] = preSum[i] - 1即可
     * 
     * 比如preSum = -2, 我们要寻找-3的index
     * 在到-4，-5分之前，肯定有一天是-3分的
     * 所以这一天是最长的
     * 
     * 可以用一个前缀和到index的Map，方便查找
     * 只存这个presum第一次出现的index
     * 因为我们希望这个interval尽可能的长
     * 
     */
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 1; i < n + 1; i++) {
            preSum[i] = preSum[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
            if (!map.containsKey(preSum[i])) {
                map.put(preSum[i], i); 
            }
            if (preSum[i] > 0) {
                res = Math.max(res, i);
            } else {
                if (map.containsKey(preSum[i] - 1)) {
                    int j = map.get(preSum[i] - 1);
                    res = Math.max(res, i - j);
                }
            }
        }
        return res;
    }
}
