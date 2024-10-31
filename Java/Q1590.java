import java.util.*;

public class Q1590 {
    /*
     * prefix sum + hash
     * 
     * 因为是sum，所以想到prefix
     * 
     * 要找到最短的[i,j]，使(sum - (prefix(j) + prefix(i))) % p == 0
     * 也就是prefix[i]和prefix[j]的mod值一样
     * 
     * 也不用真的前缀和，我们直接curMod = (curMod + n) % p来模拟即可
     * 
     * 所以我们先遍历，得到整个数组sum % p的值
     * hashmap存状态，key为mod数，val为最新一次出现的index（因为我们要最短，所以越新越好）
     * 初始状态(0, -1)，表示一个数都没有的时候mod是0
     */
    public int minSubarray(int[] nums, int p) {
        // 先计算一下和
        int sum = 0;
        for (int n : nums) {
            sum = (sum + n) % p;
        }
        // 如果当前所有和已经是0了
        // 那么不用减去任何的array
        if (sum == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int curMod = 0;
        // 最长应该是nums.length，且这个应该是不能被取到的
        int res = nums.length;
        // 由于下面 i 是从 0 开始的，前缀和下标就要从 -1 开始了
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            curMod = (curMod + nums[i]) % p;
            int target = (curMod + p - sum) % p;
            if (map.containsKey(target)) {
                res = Math.min(res, i - map.get(target));
            }
            map.put(curMod, i);
        }
        return res == nums.length ? -1 : res;
    }
}
