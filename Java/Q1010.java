import java.util.HashMap;
import java.util.Map;

public class Q1010 {
    /*
     * brute force
     * 
     */
    public int numPairsDivisibleBy601(int[] time) {
        int res = 0;
        for (int i = 0; i < time.length; i++) {
            for (int j = i + 1; j < time.length; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    res++;
                }
            }
        }
        return res;
    }

    /*
     * like 2 sum
     * 对于一个数，找到（60-num）有几个
     */
    public int numPairsDivisibleBy60(int[] time) {
        // key: t % 60, val: how many
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0;
        for (int t : time) {
            int modVal = t % 60;
            int target = modVal == 0 ? 0 : 60 - modVal;
            res += map.getOrDefault(target, 0);
            map.put(modVal, map.getOrDefault(modVal, 0) + 1);
        }
        return res;
    }
}
