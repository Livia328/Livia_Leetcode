import java.util.HashMap;
import java.util.Map;

public class Q473 {
    /*
     * 抽象：把array分成四个和相同的子集
     * -> 把所有的火柴放进4个桶
     * -> backtrack
     * 
     * 用桶的视角进行模拟，要做的选择是是否要将这个火柴装入这个桶
     * while(bucket > 0) {
     *   bucketSum = 0;
     *   for (所有火柴) {
     *     if (canAdd(bucket, num)) {
     *       加入
     *     }
     *   }
     * }
     * 
     * memo:
     * key：火柴的Used状态，val:结果
     * used: array of boolean
     * -> bit 
     * 
     * ((used >> i) & 1)来表示used[i]的true/false
     */
    Map<Integer, Boolean> memo = new HashMap<>();
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int num : matchsticks) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        // bit, 本质是used array [true, false, true, false]...
        // 用((used >> i) & 1)来表示used[i]的true/false
        int used = 0;
        int target = sum / 4;
        return backtrack(4, 0, matchsticks, 0, used, target);
    }

    /*
     * bucketNum: 还剩几个桶没装满
     * bucketSum: 当前桶的和
     */
    public boolean backtrack(int bucketNum, int bucketSum, int[] matchsticks, int start, int used, int target) {
        // 如果所有桶都装满了
        if (bucketNum == 0) {
            return true;
        }
        if (bucketSum == target) { // 当前桶装满了
            // 开始穷举下一个桶
            boolean res = backtrack(bucketNum - 1, 0, matchsticks, 0, used, target);
            memo.put(used, res);
            return res;
        }
        if (memo.containsKey(used)) {
            return memo.get(used);
        }
        // 遍历所有数
        for (int i = start; i < matchsticks.length; i++) {
            // 是否用过
            if (((used >> i) & 1) == 1) {
                continue;
            }
            // 是否可以加进当前桶
            if (bucketSum + matchsticks[i] > target) {
                continue;
            }
            // 选择
            used |= 1 << i; //将第i位标记成1
            bucketSum += matchsticks[i];
            if (backtrack(bucketNum, bucketSum, matchsticks, i + 1, used, target)) {
                return true;
            }
            // 撤销选择
            used ^= 1 << i;
            bucketSum -= matchsticks[i];
        }
        return false;
    }
}
