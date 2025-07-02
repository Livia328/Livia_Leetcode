package Java;

import java.util.*;

class Q424 {
    /*
     * 我们一般会保留出现最多的字母
     * 然后把别的字母变成出现最大的字母
     * 也就是len - max <= k
     * 
     * 所以我们需要记录出现最多的字母
     * 我们可以用sliding window
     * 
     * 用一个map来记录每个字母的出现次数
     * 用curMaxCount来update当前window的最大值
     * 
     * 当不符合条件的时候shrink left
     */
    public int characterReplacement(String s, int k) {
        int L = 0, R = 0;
        int curMaxCount = 0;
        int res = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (R < s.length()) {
            char c = s.charAt(R);
            R++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            curMaxCount = Math.max(curMaxCount, window.get(c));
            // 这里不用更新curMaxCount
            // 因为元素在不断减小，如果curMaxCount都满足不了
            // 那么就无法满足了
            while (curMaxCount + k < R - L) {
                char d = s.charAt(L);
                window.put(d, window.get(d) - 1);
                L++;
            }
            res = Math.max(res, R - L);
        }
        return res;
    }
}