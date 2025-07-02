import java.util.HashMap;

public class Q974 {
    /*
     * brute force，三重循环
     * 遍历所有的左右边界，把里面的和加起来
     * 
     * 可以用presum来简化
     * preSum[i]表示nums[0, i-1]的数字的和
     * 
     * 因为我们只需要(preSum[i] - preSum[j]) % k = 0
     * 所以需要preSum[i]和preSum[k] % k后是一样的
     * 
     * 因为preSum有可能是负数
     * 所以统一都 preSum[i] % k + k) % k
     * 
     * 比如preSum[i] = -2, k = 3
     * -2 % 3 = -2
     * 但其实我们要找的是余数为1的，这样-2 - 1 = -3就可以余了
     * 
     * 所以我们可以用一个hashmap
     * key是(preSum[i] % k + k) % k
     * value是个数
     * 
     * 遍历过去，寻找need，加上个数就行
     */
    public int subarraysDivByK(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 表示余数为0的个数为1，也就是没有的情况
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
            int need = (preSum[i + 1] % k + k) % k;
            res += map.getOrDefault(need, 0);
            map.put(need, map.getOrDefault(need, 0) + 1);
        }
        return res;
    }
}
